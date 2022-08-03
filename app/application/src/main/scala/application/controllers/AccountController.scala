package application.controllers

import application.json.AccountFormat._
import domain.account.dtos.RegisterPayload
import domain.account.serivces.AccountServiceImpl
import domain.exceptions.account.DuplicatedEmailException
import play.api.Logger
import play.api.libs.json.{JsError, JsPath, JsResultException, JsValue, Json, JsonValidationError, Writes}
import play.api.mvc._

import collection.Seq
import javax.inject._
import scala.util.{Failure, Success, Try}

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
      case dupEmail : DuplicatedEmailException => BadRequest(dupEmail.message)
      case jsExcept: JsResultException => BadRequest(jsExcept.errors.head._2.head.message)
      case _: Throwable => BadRequest
    }
  }
}

