package application.controllers

import application.json.AccountFormat._
import domain.account.dtos.RegisterPayload
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
      val register = request.body.asJson.get.as[RegisterPayload]
      accountService.save(register.email, register.username, register.password)

      } match{
          case Success(_) => Ok("Registered")
          case Failure(exception) => BadRequest(exception.getMessage)
    }
  }
}

