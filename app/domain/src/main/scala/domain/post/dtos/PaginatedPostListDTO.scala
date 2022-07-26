package domain.post.dtos

import play.api.libs.json.Json

case class PaginatedPostListDTO(pageCount: Int, posts: Seq[PostDTO])

object PaginatedPostListDTO{
  implicit lazy val format = Json.format[PaginatedPostListDTO]
}