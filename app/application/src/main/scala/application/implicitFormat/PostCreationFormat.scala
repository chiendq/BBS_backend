package application.implicitFormat

import domain.post.dto.PostCreation
import play.api.libs.json.{Json, OFormat}

object PostCreationFormat {
  implicit lazy val postCreationFormat: OFormat[PostCreation] = Json.format[PostCreation]
}
