package domain.post.dtos

import domain.post.PostConstraints._
import play.api.data.Form
import play.api.data.Forms.{ignored, mapping, text}
import play.api.libs.json.{Json, OFormat}

case class PostCreation(accountId: String,
                        title: String,
                        authorName: String,
                        content: String,
                        var thumbnail: String) {
}

object PostCreation {
  implicit lazy val postCreationFormat: OFormat[PostCreation] = Json.format[PostCreation]

  val postCreationForm: Form[PostCreation] = Form[PostCreation](
    mapping(
      ACCOUNT_ID -> text(minLength = 1),
      TITLE -> text(maxLength = 150, minLength = 1),
      AUTHOR_NAME -> text(maxLength = 50, minLength = 1),
      CONTENT -> text(minLength = 1),
      THUMBNAIL -> ignored[String]("thumbnail ne"),
    )(PostCreation.apply)(PostCreation.unapply))
}