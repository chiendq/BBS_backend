package domain.post

import domain.common.Paged
import domain.common.valueObjects.UniqueId
import domain.post.dtos.PostCreation
import domain.post.models.{Post}

import scala.util.Try

trait PostRepository {
  def createPost(postCreation: PostCreation): Try[UniqueId]

  def getPostById(id: UniqueId): Option[Post]

  def findAllWithPagination(pageSize: Int, pageNumber: Int): Paged[Post]

  def pageCount(pageSize: Int): Int
}
