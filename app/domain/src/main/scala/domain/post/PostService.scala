package domain.post

import domain.post.dto.PostCreation
import domain.post.model.{Post, PostId}

import scala.util.Try

trait PostService {
  def createPost(title: String, author: String, content: String, accountId: String, thumbnail: String): Try[PostId]


  def getPostById(id: String): Option[Post]

  def getPagination(pageSize: Int, pageNumber: Int): Try[Seq[Post]]

  def validatePageSize(pageSize: Int): Boolean

  def validatePageNumber(pageNumber: Int): Boolean

  def validatePostCreation(postCreation: PostCreation): Boolean
}
