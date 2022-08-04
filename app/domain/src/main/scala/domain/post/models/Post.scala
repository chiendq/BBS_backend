package domain.post.models

import domain.account.models.Account
import domain.common.valueObjects.UniqueId
import org.joda.time.DateTime

case class Post(id: UniqueId,
                title: String,
                content: String,
                authorName: String,
                createdAt: DateTime,
                updatedOn: DateTime,
                thumbnail: UniqueId,
                account: Option[Account] = None){

  require(title.length <= 150, "Title is too long")

  require(authorName.length <= 50, "Author name too long")

  require(title.nonEmpty,         "Title can not left blank")
  require(authorName.nonEmpty,    "Author can not left blank")
  require(content.nonEmpty,       "Content can not left blank")
  require(id.value.nonEmpty,      "UniqueId can not left blank")
  require(thumbnail.value.nonEmpty,     "Thumbnail can not left blank")
}
