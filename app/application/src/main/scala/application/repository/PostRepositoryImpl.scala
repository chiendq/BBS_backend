package application.repository

import domain.account.serivces.AccountService
import domain.common.Paged
import domain.post.PostRepository
import domain.post.dtos.PostCreation
import domain.post.model.{Post, PostId}
import infrastructure.mySqlDao.PostDao
import infrastructure.mySqlDao.exception.EntityNotFoundException
import org.joda.time.DateTime
import skinny.Pagination

import java.util.UUID
import javax.inject.Inject
import scala.util.Try

class PostRepositoryImpl @Inject()(accountService: AccountService) extends PostRepository {

  override def findAllWithPagination(pageSize: Int, pageNumber: Int): Paged[Post] = {
    val posts = PostDao.paginate(Pagination.page(pageNumber).per(pageSize)).orderBy(PostDao.defaultAlias.createdAt).apply()
    val count = PostDao.count()
    Paged(posts, count, pageNumber, pageSize)
  }

  override def pageCount(pageSize: Int): Int = {
    val records = PostDao.count()
    ((records + pageSize - 1) / pageSize).toInt
  }

  override def getPostById(id: String): Option[Post] = PostDao.findById(PostId(id))

  override def createPost(postCreation: PostCreation): Try[PostId] = {
    Try{
      val uuid = UUID.randomUUID().toString
      val currentDateTime = DateTime.now()

      PostDao.createWithAttributes(
        Symbol("id") -> uuid,
        Symbol("title") -> postCreation.title,
        Symbol("content") -> postCreation.content,
        Symbol("authorName") -> postCreation.authorName,
        Symbol("createdAt") -> currentDateTime,
        Symbol("updatedOn") -> currentDateTime,
        Symbol("thumbnail") -> postCreation.thumbnail,
        Symbol("accountId") -> postCreation.accountId
      )
    }
  }
}
