package application.implicitFormat

import domain.post.dto.PostCreation
import play.api.libs.json.Json

object PostCreationFormat {
implicit lazy val postCreationFormat = Json.format[PostCreation]
}
