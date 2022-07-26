package domain.post.model

import domain.account.model.Account
import org.joda.time.DateTime

case class Post(id: PostId,
                title: String,
                content: String,
                authorName: String,
                createdAt: DateTime,
                updatedOn: DateTime,
                thumbnail: String,
                account: Option[Account] = None){

  require(title.length <= 150, "Title is too long")

  require(authorName.length <= 50, "Author name too long")

  require(title.nonEmpty,         "Title can not left blank")
  require(authorName.nonEmpty,    "Author can not left blank")
  require(content.nonEmpty,       "Content can not left blank")
  require(id.value.nonEmpty,      "AccountId can not left blank")
  require(thumbnail.nonEmpty,     "Thumbnail can not left blank")
}
