package domain.post.services

import domain.account.model.AccountId
import domain.common.Paged
import domain.post.dtos.PostCreation
import domain.post.model.{Post, PostId}

import scala.util.Try

trait PostService {
  def createPost(postCreation: PostCreation): Try[PostId]

  def getPostById(id: String): Option[Post]

  def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[Post]]
}
