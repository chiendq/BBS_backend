package application.json

import domain.post.dtos.PostDTO
import play.api.libs.json.{Json, OFormat}

object PostDTOFormat{
  implicit lazy val format: OFormat[PostDTO] = Json.format[PostDTO]
}
