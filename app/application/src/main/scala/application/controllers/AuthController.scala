package application.controllers

import application.controllers.payload.LoginPayload
import application.jwt.SecurityConstants
import application.services.AuthService
import play.api.http.HttpEntity
import play.api.mvc._
import skinny.logging.Logger

import javax.inject.Inject
import scala.util.{Failure, Success, Try}

class AuthController @Inject()(authService: AuthService,
                               val controllerComponents: ControllerComponents)
  extends BaseController {

  import SecurityConstants._

  val logger: Logger = Logger(getClass)

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      val loginPayload = request.body.asJson.get.as[LoginPayload]

      if(! authService.validateLoginRequest(loginPayload)) throw new RuntimeException("Failed")

      authService.generateJwtToken(loginPayload)
    } match {
      case Success(token) =>
        Result.apply(
          header = ResponseHeader(OK, Map[String, String](HEADER_STRING -> (TOKEN_PREFIX + token))),
          body = HttpEntity.NoEntity)
      case Failure(exception) => Unauthorized("Email or Password is invalid")
    }
  }
}

