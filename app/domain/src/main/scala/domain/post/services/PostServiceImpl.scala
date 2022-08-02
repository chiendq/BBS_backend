package domain.post.services

import domain.account.models.AccountId
import domain.common.Paged
import domain.post.PostRepository
import domain.post.dtos.PostCreation
import domain.post.models.{Post, PostId}

import javax.inject.{Inject, Singleton}
import scala.util.Try

@Singleton
class PostServiceImpl @Inject()(postRepository: PostRepository) extends PostService {

  override def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[Post]] = Try {
    postRepository.findAllWithPagination(pageSize, pageNumber)
  }

  override def getPostById(id: String): Option[Post] = {
    postRepository.getPostById(id)
  }

  override def createPost(postCreation: PostCreation): Try[PostId] = {
    postRepository.createPost(postCreation)
  }
}
