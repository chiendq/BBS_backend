package application.controllers.actions

import application.controllers.payload.UserRequest
import application.jwt.SecurityConstants._
import play.api.http.HeaderNames
import play.api.mvc._
import application.services.AuthService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class AuthActions @Inject()(bodyParser: BodyParsers.Default, authService: AuthService)(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] {

  override def parser: BodyParser[AnyContent] = bodyParser

  override protected def executionContext: ExecutionContext = ec


  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    val bearerToken = extractBearerToken(request)
    bearerToken match {
      case Some(token) =>
        authService.validateJwt(token) match {
          case Success(claim) => block(UserRequest(request)) // token was valid - proceed!
          case Failure(t) => Future.successful(Results.Unauthorized(t.getMessage)) // token was invalid - return 401
        }
      case _ => Future.successful(Results.Unauthorized)
    }
  }

  private def extractBearerToken[A](request: Request[A]): Option[String] = {
    request.headers.get(HeaderNames.AUTHORIZATION) collect {
      case HEADER_TOKEN_REGEX(token) => token
    }
  }

}