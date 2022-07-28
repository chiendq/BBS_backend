package application.controllers

import application.controllers.payload.LoginPayload
import application.jwt.SecurityConstants
import application.services.AuthService
import domain.account.AccountService
import play.api.http.HttpEntity
import play.api.mvc._
import skinny.logging.Logger

import java.net.HttpCookie
import javax.inject.Inject
import scala.util.{Failure, Success, Try}

class AuthController @Inject()(authService: AuthService,
                               val controllerComponents: ControllerComponents,
                               accountService: AccountService)
  extends BaseController {

  import SecurityConstants._

  val logger: Logger = Logger(getClass)

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      val loginPayload = request.body.asJson.get.as[LoginPayload]

      if(! authService.validateLoginRequest(loginPayload)) throw new RuntimeException("Failed")
      val user = accountService.findByEmail(loginPayload.email).get

      val token = authService.generateJwtToken(loginPayload)

      val cookie = Cookie.apply("Bearer ",token,maxAge = Some(360000),httpOnly = true)
      Ok(user.username).withCookies(cookie)
    } match {
      case Success(result) => result

      case Failure(exception) => Unauthorized(exception.getMessage)
    }
  }
}

