package application.controllers

import application.controllers.actions.AuthActions
import application.controllers.payload.RegisterForm
import domain.post.PostService
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

  lazy val logger: Logger = Logger(getClass)

  import application.implicitFormat.PostFormat._

  def getPaginationPost(pageSize: Int, pageNumber: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val posts = postService.getPagination(pageSize, pageNumber).get
    Ok(Json.toJson(posts)(seqPostDtoFormat))
  }

  def getPostById(id: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    postService.getPostById(id) match {
      case Some(value) => Ok(Json.toJson(value)(postDtoWrites))
      case None => NotFound
    }
  }


  def createPost(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      val multipartFormData = request.body.asMultipartFormData.get

      val thumbnailUUID = UUID.randomUUID().toString

      multipartFormData.file("thumbnail")
        .map( picture =>{
          val filename = Paths.get(picture.filename).getFileName
          val fileSize = picture.fileSize
          val contentType = picture.contentType
          picture.ref.copyTo(new File(s"public/images/thumbnails/$thumbnailUUID"), replace = false)
        })

      val dataPart = multipartFormData.dataParts
      val title = dataPart("title").head
      val author = dataPart("author").head
      val content = dataPart("content").head
      val accountId = dataPart("accountId").head
      postService.createPost(title, author, content, accountId, thumbnailUUID)
    } match {
      case Success(value) => Ok
      case Failure(exception) => {
        exception.printStackTrace()
        BadRequest("Cannot created")
      }
    }
  }
}


