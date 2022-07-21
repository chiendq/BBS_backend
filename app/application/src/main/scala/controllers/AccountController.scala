package controllers


import account.AccountService
import exception.RequestTypeNotMatchException
import play.api.Logger
import play.api.mvc._

import javax.inject._
import scala.util.{Failure, Success, Try}

@Singleton
class AccountController @Inject()(val controllerComponents: ControllerComponents,
                                  accountService: AccountService) extends BaseController {
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

