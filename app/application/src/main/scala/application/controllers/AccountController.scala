package application.controllers

import domain.account.dtos.RegisterForm.registerForm
import application.services.AccountServiceImpl
import play.api.Logger
import play.api.mvc._

import javax.inject._
import scala.util.{Failure, Success, Try}

@Singleton
class AccountController @Inject()(val controllerComponents: ControllerComponents,
                                  accountService: AccountServiceImpl) extends BaseController {

  lazy val logger: Logger = Logger(getClass)

  def register(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      registerForm.bindFromRequest.value.getOrElse(throw new RuntimeException("Some thing went wrong"))
    } match {
      case Success(register) => {
        accountService.save(register.email, register.username, register.password) match {
          case Success(value) => Ok("Registered")
          case Failure(exception) => {
            exception.printStackTrace()
            Conflict(exception.getMessage)
          }
        }
      }
      case Failure(exception) =>
        BadRequest(exception.getMessage)
    }
  }
}

