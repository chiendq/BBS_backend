package application.controllers

import application.payload.RegisterForm.registerForm
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
      logger.info("ALo")

      registerForm.bindFromRequest.value.getOrElse(throw new RuntimeException("ERrorrrr"))
    } match {
      case Failure(exception) => {
        BadRequest
      }
      case Success(register) => {
        accountService.save(register.email, register.username, register.password)
        Ok("Registered")
      }
    }
  }
}

