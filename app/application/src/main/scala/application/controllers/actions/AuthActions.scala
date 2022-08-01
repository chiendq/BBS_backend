package application.controllers.actions

import application.controllers.payload.UserRequest
import application.jwt.SecurityConstants._
import play.api.http.HeaderNames
import play.api.mvc._
import application.services.AuthService
import com.auth0.jwt.exceptions.{JWTVerificationException, TokenExpiredException}
import play.api.Logger

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AuthActions @Inject()(bodyParser: BodyParsers.Default, authService: AuthService)(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] {

  override def parser: BodyParser[AnyContent] = bodyParser

  override protected def executionContext: ExecutionContext = ec

  lazy val logger: Logger = Logger(getClass)

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    Try {
      extractBearerToken(request)
    } match {
      case Failure(exception) => Future.successful(Results.Unauthorized(exception.getMessage))
      case Success(value) => value match {
        case Some(token) =>
          authService.validateJwt(token) match {
            case Success(claim) => block(UserRequest(request)) // token was valid - proceed!
            case Failure(t) => Future.successful(Results.Unauthorized(t.getMessage)) // token was invalid - return 401
          }
        case _ => Future.successful(Results.Unauthorized)
    }
    }
  }

  private def extractBearerToken[A](request: Request[A]): Option[String] = {
    val cookies = request.cookies
    cookies.get("Bearer").map(_.value).orElse(throw new JWTVerificationException("No token found!"))
  }

}