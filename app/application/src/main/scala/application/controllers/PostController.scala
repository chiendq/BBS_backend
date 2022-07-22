package application.controllers

import domain.post.PostService
import domain.post.dto.PostCreation
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import javax.inject._
import scala.util.{Failure, Success, Try}
@Singleton
class PostController @Inject()(postService: PostService, val controllerComponents: ControllerComponents) extends BaseController {
  lazy val logger: Logger = Logger(getClass)

  import application.implicitFormat.PostFormat._
  import application.implicitFormat.PostCreationFormat._
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
    Try{
      val jsonBody = request.body.asJson.get
      val postCreation = jsonBody.as[PostCreation]
      postService.createPost(postCreation)
    } match {
      case Success(value) => Ok
      case Failure(exception) => BadRequest
    }
  }
}


