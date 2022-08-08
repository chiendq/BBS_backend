package application.controllers

import application.json.AccountFormat._
import domain.account.dtos.RegisterPayload
import domain.account.serivces.AccountServiceImpl
import domain.exceptions.account.DuplicatedException
import play.api.Logger
import play.api.libs.json.JsResultException
import play.api.mvc._

import javax.inject._

@Singleton
class AccountController @Inject()(val controllerComponents: ControllerComponents,
                                  accountService: AccountServiceImpl) extends BaseController {

  lazy val logger: Logger = Logger(getClass)

  def register(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    try {
      val register = request.body.asJson.get.as[RegisterPayload]
      accountService.save(register.email, register.username, register.password)
      Ok("Registered")
      } catch {
      case ill : IllegalArgumentException => BadRequest(ill.getMessage)
      case jsExcept: JsResultException => BadRequest(jsExcept.errors.head._2.head.message)
      case th: Throwable => {
        th.printStackTrace()
        InternalServerError
      }
    }
  }
}

