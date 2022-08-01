package application.controllers

import application.services.AuthServiceImpl
import application.forms.LoginForm.loginForm
import application.json.AccountFormat._
import domain.account.dtos.LoginResponseDTO
import domain.account.serivces.AccountService

import play.api.libs.json.Json
import play.api.mvc._
import skinny.logging.Logger

import javax.inject.Inject
import scala.util.{Failure, Success, Try}

class AuthController @Inject()(authService: AuthServiceImpl,
                               val controllerComponents: ControllerComponents,
                               accountService: AccountService)
  extends BaseController {

  val logger: Logger = Logger(getClass)

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      val loginPayload = loginForm.bindFromRequest().value.get

      if(! authService.validateLoginRequest(loginPayload)) throw new RuntimeException("Failed")
      val user = accountService.findByEmail(loginPayload.email).get

      val token = authService.generateJwtToken(loginPayload)

      val cookie = Cookie.apply("Bearer",token,maxAge = Some(360000),httpOnly = true)

      val loginResponseDTO = LoginResponseDTO(user.id.value, user.username)
      Ok(Json.toJson(loginResponseDTO)).withCookies(cookie)
    } match {
      case Success(result) => result
      case Failure(exception) => Unauthorized(exception.getMessage)
    }
  }

  def logout(): Action[AnyContent] = Action { implicit request: Request[AnyContent] => {
    Ok.withCookies(Cookie("Bearer",""))
    }
  }
}

