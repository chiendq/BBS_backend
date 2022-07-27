package domain.post.dtos

import domain.post.PostConstraints._
import play.api.data.Form
import play.api.data.Forms.{ignored, mapping, text}
import play.api.libs.json.{Json, OFormat}

case class PostCreation(accountId: String,
                        title: String,
                        authorName: String,
                        content: String,
                        thumbnail: String) {
  //  require(title.length <= 150, "Title is too long")
  //
  //  require(authorName.length <= 50, "Author name too long")
  //
  //  require(title.nonEmpty,     "Title can not left blank")
  //  require(authorName.nonEmpty,    "Author can not left blank")
  //  require(content.nonEmpty,   "Content can not left blank")
  //  require(accountId.nonEmpty, "AccountId can not left blank")
  //  require(thumbnail.nonEmpty, "Thumbnail can not left blank")
}

object PostCreation {
  implicit lazy val postCreationFormat: OFormat[PostCreation] = Json.format[PostCreation]

  val postCreationForm = Form[PostCreation](
    mapping(
      ACCOUNT_ID -> text(minLength = 1),
      TITLE -> text(maxLength = 150, minLength = 1),
      AUTHOR_NAME -> text(maxLength = 50, minLength = 1),
      CONTENT -> text(minLength = 1),
      THUMBNAIL -> ignored(THUMBNAIL),
    )(PostCreation.apply)(PostCreation.unapply))
}