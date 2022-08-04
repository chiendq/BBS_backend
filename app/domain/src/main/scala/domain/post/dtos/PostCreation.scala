package domain.post.dtos

import domain.common.valueObjects.UniqueId

case class PostCreation(
                         accountId: UniqueId,
                         title: String,
                         authorName: String,
                         content: String,
                         thumbnail: String) {
}
