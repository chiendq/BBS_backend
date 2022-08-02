package application.controllers.actions

import application.jwt.SecurityConstants.TOKEN_NAME
import com.auth0.jwt.exceptions.JWTVerificationException
import domain.auth.JWT
import play.api.Logger
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AuthActions @Inject()(
                             bodyParser: BodyParsers.Default,
                             jwt: JWT
                           )(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] {

  lazy val logger: Logger = Logger(getClass)
  override protected def executionContext: ExecutionContext = ec

  override def parser: BodyParser[AnyContent] = bodyParser

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    Try {
      request.cookies
        .get(TOKEN_NAME)
        .map(_.value)
        .getOrElse(throw new JWTVerificationException("No token found!"))
    } match {
      case Success(value) =>
        jwt.validate(value) match {
          case Success(claim) => block(UserRequest(claim, request))
          case Failure(t) => Future.successful(Results.Unauthorized(t.getMessage))
        }
      case Failure(exception) => Future.successful(Results.Unauthorized(exception.getMessage))
    }
  }

}