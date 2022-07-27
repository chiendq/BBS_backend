package application.controllers

import application.controllers.actions.AuthActions
import domain.post.PostConstraints._
import domain.post.PostService
import domain.post.dtos.PostCreation._
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
                               postService: PostService,
                               controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {

  import application.json.PagedFormat._

  lazy val logger: Logger = Logger(getClass)

  def getPaginatedPosts(pageSize: Int, pageNumber: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      postService.getPaginatedPostList(pageSize, pageNumber).get
    } match {
      case Success(posts) => Ok(Json.toJson(posts))
      case Failure(_) => BadRequest
    }
  }

  def getPostById(id: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    postService.getPostById(id) match {
      case Some(value) => Ok(Json.toJson(value))
      case None => NotFound
    }
  }

  def createPost(): Action[AnyContent] = authActions { implicit request: Request[AnyContent] =>
    Try {
      val multipartFormData = request.body.asMultipartFormData.get

      val thumbnailUUID = UUID.randomUUID().toString
      multipartFormData.file(THUMBNAIL)
        .map(picture => {
          val filename = Paths.get(picture.filename).getFileName
          val fileSize = picture.fileSize
          val contentType = picture.contentType.get

          picture.ref.copyTo(new File(s"public/images/thumbnails/$thumbnailUUID.png"), replace = false)

        }).getOrElse(throw RequestTypeNotMatchException("Missing thumbnail"))
      val dataPart = multipartFormData.dataParts

      val postCreation = postCreationForm.bindFromRequest(dataPart).get

      postCreation.thumbnail = thumbnailUUID

      postService.createPost(postCreation)
    } match {
      case Success(_) => Created("Create new post successfully!")
      case Failure(exception) => exception match {
        case requestType: RequestTypeNotMatchException => UnprocessableEntity(requestType.message)
        case _ =>
          exception.printStackTrace()
          BadRequest(exception.getMessage)
      }
    }
  }
}


