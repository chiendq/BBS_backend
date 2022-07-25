package application.services

import domain.post.dto.PostCreation
import domain.post.{PostRepository, PostService}
import domain.post.model.{Post, PostId}

import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success, Try}

// NEED TO BE VALIDATED HERE

/**
 * Use Value Object to help validation
 */
@Singleton
class PostServiceImpl @Inject()(postRepository: PostRepository) extends PostService{

  /**
   * TODO : validate
   */
  override def getPagination(pageSize: Int, pageNumber: Int): Try[Seq[Post]] =Try{
    postRepository.findAllWithPagination(pageSize, pageNumber)
  }

  override def validatePageSize(pageSize: Int): Boolean = {
    if(pageSize < postRepository.count()) false
    else true
  }

  /**
   * TODO: Validate page number
   * @param pageNumber
   * @return
   */
  override def validatePageNumber(pageNumber: Int): Boolean = ???

  override def getPostById(id: String): Option[Post] = postRepository.getPostById(id)

  override def createPost(postCreation: PostCreation): Try[PostId] = {
    postRepository.createPost(postCreation)
  }

  /**
   * TODO: validate PostCreation
   */
  override def validatePostCreation(postCreation: PostCreation): Boolean = ???
}
