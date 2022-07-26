package domain.post

import domain.common.Paged
import domain.post.dtos.PostCreation
import domain.post.model.{Post, PostId}

import scala.util.Try

trait PostRepository {
  def createPost(postCreation: PostCreation): Try[PostId]

  def getPostById(id: String): Option[Post]

  def findAllWithPagination(pageSize: Int, pageNumber: Int): Paged[Post]

  def pageCount(pageSize: Int): Int
}
