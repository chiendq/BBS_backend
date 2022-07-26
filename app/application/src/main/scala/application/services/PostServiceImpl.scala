package application.services

import application.converter.PostConverter
import domain.common.Paged
import domain.post.dtos.{PaginatedPostListDTO, PostCreation, PostDTO}
import domain.post.model.{Post, PostId}
import domain.post.{PostRepository, PostService}

import javax.inject.{Inject, Singleton}
import scala.tools.nsc.doc.html.Page
import scala.util.Try

@Singleton
class PostServiceImpl @Inject()(postRepository: PostRepository) extends PostService {

  override def getPaginatedPostList(pageSize: Int, pageNumber: Int): Try[Paged[PostDTO]] = Try {
    postRepository.findAllWithPagination(pageSize, pageNumber).map(PostConverter.toDto)
  }

  override def getPostById(id: String): Option[PostDTO] = {
    val post = postRepository.getPostById(id)
    Some(PostConverter.toDto(post.get))
  }

  override def createPost(postCreation: PostCreation): Try[PostId] = {
    postRepository.createPost(postCreation)
  }
}
