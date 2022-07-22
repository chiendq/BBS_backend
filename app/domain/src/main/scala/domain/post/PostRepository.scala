package domain.post

import domain.post.dto.PostCreation
import domain.post.model.{Post, PostId}

import scala.util.Try

trait PostRepository {
  def createPost(postCreation: PostCreation): Try[PostId]
  def getPostById(id: String): Option[Post]

  def findAllWithPagination(pageSize: Int, pageNumber: Int): Seq[Post]

  def count(): Long
}
