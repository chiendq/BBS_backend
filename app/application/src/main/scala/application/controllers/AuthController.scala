package application.controllers

import application.implicitFormat.LoginRequestFormat
import application.jwt.SecurityConstants
import application.payload.LoginRequest
import application.services.AuthService
import play.api.http.HttpEntity
import play.api.mvc._
import skinny.logging.Logger

import javax.inject.Inject
import scala.util.{Failure, Success, Try}

class AuthController @Inject()(authService: AuthService,
                               val controllerComponents: ControllerComponents)
  extends BaseController {

  import LoginRequestFormat._
  import SecurityConstants._

  val logger: Logger = Logger(getClass)

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Try {
      val loginRequest = request.body.asJson.get.as[LoginRequest]

//      val hasedPassword = authService.hashPassword(loginRequest.password)
//      logger.info(s"HashedPassword: $hasedPassword")
//      val isMatch = authService.validatePassword(loginRequest.password, hasedPassword)
//      logger.info(s"Password is match : $isMatch")

      authService.generateJwtToken(loginRequest)
    } match {
      case Success(token) =>
        Result.apply(
          header = ResponseHeader(OK, Map[String, String](HEADER_STRING -> (TOKEN_PREFIX + token))),
          body = HttpEntity.NoEntity)
      case Failure(exception) => BadRequest
    }
  }
}

