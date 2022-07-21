package controllers

import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import skinny.Pagination
import javax.inject._
import mySqlDao.PostDao
import implicitFormat.PostFormat._
@Singleton
class PostController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  lazy val logger: Logger = Logger(getClass)

  def getAllPost: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val pagination = PostDao.findAllWithPagination(Pagination(10, 1))
    Ok(Json.toJson(pagination))
  }


}


