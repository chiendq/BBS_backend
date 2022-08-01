package domain.post.dtos

import domain.account.model.AccountId

case class PostCreation(
                         accountId: AccountId,
                         title: String,
                         authorName: String,
                         content: String,
                         thumbnail: String) {
}
