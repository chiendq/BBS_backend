package bbs.controllers

import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

import javax.inject._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {


    def index() = Action { implicit request: Request[AnyContent] =>

      Ok("Application")
  }
}
