package application.repository

import application.exception.EntityNotFoundException
import domain.account.AccountService
import domain.post.PostRepository
import domain.post.dto.PostCreation
import domain.post.model.{Post, PostId}
import infrastructure.mySqlDao.PostDao
import org.joda.time.DateTime
import skinny.Pagination

import java.util.UUID
import javax.inject.Inject
import scala.util.Try

/**
 * FIXME : not sure that calling "Service" or "Repository" layer
 */
class PostRepositoryImpl @Inject()(accountService: AccountService) extends PostRepository {

  override def findAllWithPagination(pageSize: Int, pageNumber: Int): Seq[Post] = {
    PostDao.paginate(Pagination.page(pageNumber).per(pageSize)).orderBy().apply()
  }

  /**
   * TODO: implement count the records in database
   *
   * @return
   */
  override def count(): Long = ???

  override def getPostById(id: String): Option[Post] = PostDao.findById(PostId(id))

  /*
  NOT COMPLETED YET : MISSING THUMBNAIL PATH
   */
  override def createPost(postCreation: PostCreation): Try[PostId] = {
    if (!accountService.isExistAccountId(postCreation.accountId)) throw EntityNotFoundException("Entity Not Found")
    Try{
      val uuid = UUID.randomUUID()
      val currentDateTime = DateTime.now()
      PostDao.createWithAttributes(
        Symbol("id") -> uuid.toString,
        Symbol("title") -> postCreation.title,
        Symbol("content") -> postCreation.content,
        Symbol("authorName") -> postCreation.author,
        Symbol("createdAt") -> currentDateTime,
        Symbol("updatedOn") -> currentDateTime,
        Symbol("thumbnail") -> "/public/images/thumbnails/123",
        Symbol("accountId") -> postCreation.accountId
      )
    }
  }
}
