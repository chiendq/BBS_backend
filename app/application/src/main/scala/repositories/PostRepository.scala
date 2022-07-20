package repositories

trait PostRepository {
  def findAll(): Seq[Post]

  def save(post: PostCreation, accountId: AccountId): Option[PostId]
}
