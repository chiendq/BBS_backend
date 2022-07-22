package application.controllers

import application.exception.RequestTypeNotMatchException
import application.service.AccountServiceImpl
import play.api.Logger
import play.api.mvc._

import javax.inject._
import scala.util.{Failure, Success, Try}

@Singleton
class AccountController @Inject()(val controllerComponents: ControllerComponents,
                                  accountService: AccountServiceImpl) extends BaseController {
  lazy val logger: Logger = Logger(getClass)


  def login: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      throw RequestTypeNotMatchException()
    } match {
      case Success(value) => Ok
      case Failure(exception) => BadRequest
    }
  }
}

