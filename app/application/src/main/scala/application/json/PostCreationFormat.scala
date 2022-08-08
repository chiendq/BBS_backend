package application.json

import domain.common.valueObjects.UniqueId
import domain.post.PostConstants.{ACCOUNT_ID, AUTHOR_NAME, CONTENT, THUMBNAIL, TITLE}
import domain.post.dtos.PostCreation
import play.api.data.Form
import play.api.data.Forms.{ignored, mapping, text}
import play.api.data.validation.Constraint

object PostCreationFormat {
  def postCreationForm(accountId: UniqueId, thumbnail: String) = Form[PostCreation](
    mapping(
      ACCOUNT_ID -> ignored(accountId),
      TITLE -> text(maxLength = 150, minLength = 1),
      AUTHOR_NAME -> text(maxLength = 50, minLength = 1),
      CONTENT -> text(minLength = 1),
      THUMBNAIL -> ignored[String](thumbnail),
    )(PostCreation.apply)(PostCreation.unapply))

}
