package application.converter

import domain.post.dtos.PostDTO
import domain.post.model.Post
import application.converter.DateTimeConverter._
object PostConverter {

  def toDto(post: Post): PostDTO = {
    PostDTO(post.id.value,
      post.title,
      post.content,
      post.authorName,
      toJodaDateTimeString(post.createdAt),
      toJodaDateTimeString(post.updatedOn),
      post.thumbnail
    )
  }
}
