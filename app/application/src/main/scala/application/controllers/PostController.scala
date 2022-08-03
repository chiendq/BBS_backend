package application.controllers

import application.controllers.actions.AuthActions
import application.converter.PostConverter._
import application.json.PagedFormat._
import application.json.PostCreationFormat.postCreationForm
import application.json.PostDTOFormat._
import application.services.AuthServiceImpl
import domain.exceptions.post.{InvalidImageTypeException, PostException, RequestTypeMissMatchException}
import domain.post.PostConstants._
import domain.post.services.PostService
import play.api.Logger
import play.api.libs.Files.TemporaryFile
import play.api.libs.json.{JsResultException, Json}
import play.api.mvc.MultipartFormData.FilePart
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
      postService.getPaginatedPostList(pageSize, pageNumber).get.map(toDto)
    } match {
      case Success(posts) => Ok(Json.toJson(posts))
      case Failure(_) => BadRequest
    }
  }

  def getPostById(id: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    postService.getPostById(id) match {
      case Some(value) => {
        Ok(Json.toJson(toDto(value)))
      }
      case None => NotFound(s"Post not found with id $id")
    }
  }

  def createPost(): Action[AnyContent] = authActions { implicit request =>
    try {
      val multipartFormData = request.body.asMultipartFormData.get

      val thumbnailUUID = multipartFormData
        .file("file")
        .map(uploadThumbnail)
        .getOrElse(throw RequestTypeMissMatchException("Missing thumbnail"))

      val dataPart = multipartFormData.dataParts

      val form = postCreationForm(
        request.accountId,
        thumbnailUUID
      ).bindFromRequest(dataPart)

      form.fold(
        form => throw new RuntimeException(form.errors.mkString(", ")),
        postCreation => postService.createPost(postCreation)
      )
      Created("Create post successfully!")
    }catch {
      case postExcept: PostException => BadRequest(postExcept.getMessage)
      case _: Throwable => BadRequest
    }
  }


  private def uploadThumbnail(file: FilePart[TemporaryFile]): String = {
    val thumbnailUUID = UUID.randomUUID().toString
    val filename = Paths.get(file.filename).getFileName
    val fileSize = file.fileSize
    val contentType = file.contentType.get

    if(! SUPPORT_IMG.contains(contentType)) throw InvalidImageTypeException("Invalid thumbnail content type!")
    file.ref.copyTo(new File(s"$THUMBNAIL_PATH$thumbnailUUID.png"), replace = false)
    thumbnailUUID
  }
}


