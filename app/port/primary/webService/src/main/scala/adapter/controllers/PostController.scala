package adapter.controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}

import javax.inject._

@Singleton
class PostController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {


  def getAllPost: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    Ok("Post Controller Here")
  }
}

