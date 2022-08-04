package domain.post.services

import domain.common.Paged
import domain.common.valueObjects.UniqueId
import domain.post.PostRepository
import domain.post.dtos.PostCreation
import domain.post.models.{Post}

import javax.inject.{Inject, Singleton}
import scala.util.Try

@Singleton
class PostServiceImpl @Inject()(postRepository: PostRepository) extends PostService {

  override def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[Post]] = Try {
    postRepository.findAllWithPagination(pageSize, pageNumber)
  }

  override def getPostById(id: UniqueId): Option[Post] = {
    postRepository.getPostById(id)
  }

  override def createPost(postCreation: PostCreation): Try[UniqueId] = {
    postRepository.createPost(postCreation)
  }
}
