package domain.post

import domain.common.Paged
import domain.post.dtos.{PostCreation, PostDTO}
import domain.post.model.PostId

import scala.util.Try

trait PostService {
  def createPost(postCreation: PostCreation): Try[PostId]

  def getPostById(id: String): Option[PostDTO]

  def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[PostDTO]]
}
