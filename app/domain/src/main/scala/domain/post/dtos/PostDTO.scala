package domain.post.dtos

import play.api.libs.json.Json

case class PostDTO(id: String,
                   title: String,
                   content: String,
                   authorName: String,
                   createdAt: String,
                   updatedOn: String)

object PostDTO{
  implicit lazy val format = Json.format[PostDTO]
}