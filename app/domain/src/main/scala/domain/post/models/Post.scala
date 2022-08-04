package domain.post.models

import domain.account.models.Account
import domain.common.valueObjects.UniqueId
import domain.post.valueObjects.{AuthorName, Content, Title}
import org.joda.time.DateTime

case class Post(id: UniqueId,
                title: Title,
                content: Content,
                authorName: AuthorName,
                createdAt: DateTime,
                updatedOn: DateTime,
                thumbnail: UniqueId,
                account: Option[Account] = None){
}
