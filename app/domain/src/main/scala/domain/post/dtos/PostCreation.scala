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
