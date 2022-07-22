package domain.post

import domain.post.dto.PostCreation
import domain.post.model.{Post, PostId}

trait PostRepository {
  def createPost(postCreation: PostCreation): Option[PostId]

  def getPostById(id: String): Option[Post]

  def findAllWithPagination(pageSize: Int, pageNumber: Int): Seq[Post]

  def count(): Long
}
