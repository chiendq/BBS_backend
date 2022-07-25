import application.repository.{AccountRepositoryImpl, PostRepositoryImpl}
import application.services.{AccountServiceImpl, PostServiceImpl}
import com.google.inject.AbstractModule
import domain.account.{AccountRepository, AccountService}
import domain.post.{PostRepository, PostService}
import play.api.Logger

class Module extends AbstractModule{
  val logger = Logger(getClass)
  override def configure(): Unit = {
    bind(classOf[PostRepository]).to(classOf[PostRepositoryImpl])
    bind(classOf[AccountRepository]).to(classOf[AccountRepositoryImpl])

    bind(classOf[PostService]).to(classOf[PostServiceImpl])
    bind(classOf[AccountService]).to(classOf[AccountServiceImpl])
  }
}
