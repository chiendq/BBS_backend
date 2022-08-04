package application.controllers

import application.json.AccountFormat._
import application.jwt.SecurityConstants._
import domain.account.dtos.{LoginRequestDTO, LoginResponseDTO}
import domain.account.serivces.AccountService
import domain.auth.AuthService
import domain.exceptions.RequestRejectException
import domain.exceptions.account.AuthenticationFailedException
import play.api.libs.json.Json
import play.api.mvc._
import skinny.logging.Logger

import javax.inject.Inject

class AuthController @Inject()(authService: AuthService,
                               val controllerComponents: ControllerComponents,
                               accountService: AccountService)
  extends BaseController {

  val logger: Logger = Logger(getClass)

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    try {

      val json = request.body.asJson.getOrElse(throw RequestRejectException())
      val loginDTO = json.as[LoginRequestDTO]
      val email = loginDTO.email

      authService.validateLoginRequest(loginDTO) match {
        case false => throw AuthenticationFailedException("Incorrect username or password")
        case true =>
          val user = accountService.findByEmail(email).get

          val token = authService.generateJwtToken(email)

          val cookie = Cookie.apply(TOKEN_NAME, token, maxAge = Some(EXPIRATION_TIME), httpOnly = true)

          val loginResponseDTO = LoginResponseDTO(user.id, user.username)
          Ok(Json.toJson(loginResponseDTO)).withCookies(cookie)
      }
    } catch {
      case authFailed: AuthenticationFailedException => Unauthorized(authFailed.message)
      case illArEx: IllegalArgumentException => BadRequest(illArEx.getMessage)
      case reject: RequestRejectException => BadRequest
      case e: Throwable => InternalServerError
    }
  }

  def logout(): Action[AnyContent] = Action { implicit request: Request[AnyContent] => {
    Ok.withCookies(Cookie(TOKEN_NAME, ""))
  }
  }
}

