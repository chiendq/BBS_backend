package application.controllers

import application.json.AccountFormat._
import application.jwt.SecurityConstants.TOKEN_NAME
import domain.account.dtos.{LoginRequestDTO, LoginResponseDTO}
import domain.account.serivces.AccountService
import domain.auth.AuthService
import infrastructure.exception.AuthenticationException
import play.api.libs.json.Json
import play.api.mvc._
import skinny.logging.Logger

import javax.inject.Inject
import scala.util.{Failure, Success, Try}

class AuthController @Inject()(authService: AuthService,
                               val controllerComponents: ControllerComponents,
                               accountService: AccountService)
  extends BaseController {

  val logger: Logger = Logger(getClass)

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      val loginDTO = request.body.asJson.get.as[LoginRequestDTO]

      val email = loginDTO.email

      if(! authService.validateLoginRequest(loginDTO)) throw AuthenticationException("Incorrect username or password")

      val user = accountService.findByEmail(email.value).get

      val token = authService.generateJwtToken(email)

      val cookie = Cookie.apply(TOKEN_NAME,token,maxAge = Some(360000),httpOnly = true)

      val loginResponseDTO = LoginResponseDTO(user.id.value, user.username)
      Ok(Json.toJson(loginResponseDTO)).withCookies(cookie)
    } match {
      case Success(result) => result
      case Failure(exception) => Unauthorized(exception.getMessage)
    }
  }

  def logout(): Action[AnyContent] = Action { implicit request: Request[AnyContent] => {
    Ok.withCookies(Cookie(TOKEN_NAME,""))
    }
  }
}

