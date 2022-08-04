package application.repositories

import domain.common.CommonConstants.ID
import domain.common.Paged
import domain.post.PostConstants._
import domain.post.PostRepository
import domain.post.dtos.PostCreation
import domain.post.models.{Post, PostId}
import infrastructure.mySqlDao.PostDao

import org.joda.time.DateTime
import skinny.Pagination

import java.util.UUID
import scala.util.Try

class PostRepositoryImpl () extends PostRepository {

  override def findAllWithPagination(pageSize: Int, pageNumber: Int): Paged[Post] = {
    val posts = PostDao
                  .paginate(Pagination.page(pageNumber).per(pageSize))
                  .orderBy(PostDao.defaultAlias.createdAt).apply()
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
        Symbol(ID)          -> uuid,
        Symbol(TITLE)       -> postCreation.title,
        Symbol(CONTENT)     -> postCreation.content,
        Symbol(AUTHOR_NAME) -> postCreation.authorName,
        Symbol(CREATED_AT)  -> currentDateTime,
        Symbol(UPDATED_ON)  -> currentDateTime,
        Symbol(THUMBNAIL)   -> postCreation.thumbnail,
        Symbol(ACCOUNT_ID)  -> postCreation.accountId.value
      )
    }
  }
}
