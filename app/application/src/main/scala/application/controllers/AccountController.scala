package application.controllers

import application.forms.RegisterForm.registerForm
import domain.account.serivces.AccountServiceImpl
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
      val form = registerForm.bindFromRequest()

      val register = form.fold(
        form => throw new IllegalArgumentException(form.errors.head.message),
        value => value
      )
      accountService.save(register.email, register.username, register.password)

      } match{
          case Success(_) => Ok("Registered")
          case Failure(exception) => BadRequest(exception.getMessage)
    }
  }
}

