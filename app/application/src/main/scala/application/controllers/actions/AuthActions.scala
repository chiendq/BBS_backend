package application.controllers.actions

import application.services.AuthServiceImpl
import play.api.Logger
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AuthActions @Inject()(bodyParser: BodyParsers.Default, authService: AuthServiceImpl)(implicit ec: ExecutionContext)
  extends ActionBuilder[Request, AnyContent] {

  lazy val logger: Logger = Logger(getClass)
  override protected def executionContext: ExecutionContext = ec

  override def parser: BodyParser[AnyContent] = bodyParser

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = {
    Try {
      authService.extractBearerToken(request).get
    } match {
      case Success(value) =>
        authService.validateJwt(value) match {
          case Success(claim) => block(request)
          case Failure(t) => Future.successful(Results.Unauthorized(t.getMessage))
        }
      case Failure(exception) => Future.successful(Results.Unauthorized(exception.getMessage))
    }
  }

}