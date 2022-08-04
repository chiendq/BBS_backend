package domain.post.services

import domain.common.Paged
import domain.common.valueObjects.UniqueId
import domain.post.dtos.PostCreation
import domain.post.models.{Post}

import scala.util.Try

trait PostService {
  def createPost(postCreation: PostCreation): Try[UniqueId]

  def getPostById(id: UniqueId): Option[Post]

  def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[Post]]
}
