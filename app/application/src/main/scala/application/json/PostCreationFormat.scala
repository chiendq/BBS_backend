package application.json

import domain.post.PostConstraints.{ACCOUNT_ID, AUTHOR_NAME, CONTENT, THUMBNAIL, TITLE}
import domain.post.dtos.PostCreation
import play.api.data.Form
import play.api.data.Forms.{ignored, mapping, text}
import play.api.libs.json.{Json, OFormat}


object PostCreationFormat {
  implicit lazy val postCreationFormat: OFormat[PostCreation] = Json.format[PostCreation]

  val postCreationForm: Form[PostCreation] = Form[PostCreation](
    mapping(
      ACCOUNT_ID -> text(minLength = 1),
      TITLE -> text(maxLength = 150, minLength = 1),
      AUTHOR_NAME -> text(maxLength = 50, minLength = 1),
      CONTENT -> text(minLength = 1),
      THUMBNAIL -> ignored[String]("default thumbnail"),
    )(PostCreation.apply)(PostCreation.unapply))
}