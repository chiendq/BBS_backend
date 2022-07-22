package application.repository

import domain.account.model.AccountId
import domain.post.PostRepository
import domain.post.dto.PostCreation
import domain.post.model.{Post, PostId}
import infrastructure.mySqlDao.PostDao
import org.joda.time.DateTime
import skinny.Pagination

import java.util.UUID
import javax.inject.Inject
import scala.util.{Failure, Success, Try}

class PostRepositoryImpl @Inject()() extends PostRepository {

  override def findAllWithPagination(pageSize: Int, pageNumber: Int): Seq[Post] = {
    PostDao.paginate(Pagination.page(pageNumber).per(pageSize)).orderBy().apply()
  }

  override def count(): Long = ???

  override def getPostById(id: String): Option[Post] = PostDao.findById(PostId(id))

  /*
  NOT COMPLETED YET : MISSING THUMBNAIL PATH
   */
  override def createPost(postCreation: PostCreation): Option[PostId] = {
    Try {
      val uuid = UUID.randomUUID()
      val currentDateTime = DateTime.now()
      PostDao.createWithAttributes(
        Symbol("id") -> uuid.toString,
        Symbol("title") -> postCreation.tittle,
        Symbol("content") -> postCreation.content,
        Symbol("authorName") -> postCreation.author,
        Symbol("createdAt") -> currentDateTime,
        Symbol("updatedOn") -> currentDateTime,
        Symbol("thumbnail") -> "/public/images/thumbnails/123",
        Symbol("accountId") -> postCreation.accountId
      )
    } match {
      case Success(value) => Some(value)
      case Failure(exception) => None
    }
  }
}
