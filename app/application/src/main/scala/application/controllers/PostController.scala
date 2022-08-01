package application.controllers

import application.controllers.actions.AuthActions
import application.converter.PostConverter
import application.json.PagedFormat._
import application.json.PostCreationFormat.postCreationForm
import application.json.PostDTOFormat._
import application.services.AuthServiceImpl
import com.auth0.jwt.exceptions.JWTVerificationException
import domain.post.services.PostService
import infrastructure.mySqlDao.exception.RequestTypeNotMatchException
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._

import java.io.File
import java.nio.file.Paths
import java.util.UUID
import javax.inject._
import scala.util.{Failure, Success, Try}

@Singleton
class PostController @Inject()(authActions: AuthActions,
                               authService: AuthServiceImpl,
                               postService: PostService,
                               controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {

  lazy val logger: Logger = Logger(getClass)

  def getPaginatedPosts(pageSize: Int, pageNumber: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      postService.getPaginatedPostList(pageSize, pageNumber).get.map(PostConverter.toDto)
    } match {
      case Success(posts) => Ok(Json.toJson(posts))
      case Failure(_) => BadRequest
    }
  }

  def getPostById(id: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    postService.getPostById(id) match {
      case Some(value) => {
        val postDTO = PostConverter.toDto(value)
        Ok(Json.toJson(postDTO))
      }
      case None => NotFound
    }
  }

  def createPost(): Action[AnyContent] = authActions { implicit request: Request[AnyContent] =>
    Try {

      val multipartFormData = request.body.asMultipartFormData.get

      val thumbnailUUID = UUID.randomUUID().toString

      multipartFormData.file("file")
        .map(picture => {
          val filename = Paths.get(picture.filename).getFileName
          val fileSize = picture.fileSize
          val contentType = picture.contentType.get

          picture.ref.copyTo(new File(s"public/images/thumbnails/$thumbnailUUID.png"), replace = false)

        }).getOrElse(throw RequestTypeNotMatchException("Missing thumbnail"))

      val dataPart = multipartFormData.dataParts

      val form = postCreationForm.bindFromRequest(dataPart)
      val errors = form.errors

      if(!errors.isEmpty) throw new RuntimeException(errors.mkString(", "))

      val postCreation = form.get
      val tokenAccountId = authService.extractSubject(request)

      if( postCreation.accountId != tokenAccountId) throw new JWTVerificationException("Authorization Error")

      postCreation.thumbnail = thumbnailUUID

      postService.createPost(postCreation)
    } match {
      case Success(_) => Created("Create new post successfully!")
      case Failure(exception) => exception match {
        case requestType: RequestTypeNotMatchException => UnprocessableEntity(requestType.message)
        case _ => BadRequest(exception.getMessage)
      }
    }
  }
}


