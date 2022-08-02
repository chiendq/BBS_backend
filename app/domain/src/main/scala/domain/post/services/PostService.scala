package domain.post.services

import domain.account.models.AccountId
import domain.common.Paged
import domain.post.dtos.PostCreation
import domain.post.models.{Post, PostId}

import scala.util.Try

trait PostService {
  def createPost(postCreation: PostCreation): Try[PostId]

  def getPostById(id: String): Option[Post]

  def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[Post]]
}
