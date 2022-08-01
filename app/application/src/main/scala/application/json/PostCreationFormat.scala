package application.json

import domain.account.model.AccountId
import domain.post.PostConstraints.{ACCOUNT_ID, AUTHOR_NAME, CONTENT, THUMBNAIL, TITLE}
import domain.post.dtos.PostCreation
import play.api.data.Form
import play.api.data.Forms.{ignored, mapping, text}
import play.api.libs.json.{Json, OFormat}


object PostCreationFormat {
  def postCreationForm(accountId: AccountId, thumbnail: String) = Form[PostCreation](
    mapping(
      ACCOUNT_ID -> ignored(accountId),
      TITLE -> text(maxLength = 150, minLength = 1),
      AUTHOR_NAME -> text(maxLength = 50, minLength = 1),
      CONTENT -> text(minLength = 1),
      THUMBNAIL -> ignored[String](thumbnail),
    )(PostCreation.apply)(PostCreation.unapply))

}
