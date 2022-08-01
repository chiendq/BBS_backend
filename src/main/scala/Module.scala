import application.jwt.{JWTImpl, PasswordHashImpl}
import application.repository.{AccountRepositoryImpl, PostRepositoryImpl}
import application.services.AuthServiceImpl
import com.google.inject.AbstractModule
import domain.account.serivces.{AccountService, AccountServiceImpl}
import domain.account.AccountRepository
import domain.auth.{AuthService, JWT, PasswordHash}
import domain.post.services.{PostService, PostServiceImpl}
import domain.post.PostRepository
import play.api.Logger

class Module extends AbstractModule{
  val logger = Logger(getClass)
  override def configure(): Unit = {
    bind(classOf[PostRepository]).to(classOf[PostRepositoryImpl])
    bind(classOf[AccountRepository]).to(classOf[AccountRepositoryImpl])

    bind(classOf[PostService]).to(classOf[PostServiceImpl])
    bind(classOf[AccountService]).to(classOf[AccountServiceImpl])
    bind(classOf[AuthService]).to(classOf[AuthServiceImpl])
    bind(classOf[JWT]).toInstance(JWTImpl)
    bind(classOf[PasswordHash]).toInstance(PasswordHashImpl)
  }
}
