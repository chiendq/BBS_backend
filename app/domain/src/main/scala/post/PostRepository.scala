package post

import account.AccountId

trait PostRepository {
  def findAll(): Seq[Post]

  def save(title: String,
           content: String,
           authorName: String,
           thumbnail: String,
           accountId: AccountId): Option[PostId]
}
