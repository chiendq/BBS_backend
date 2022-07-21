package account

trait AccountRepository {

  def save(email: String, username: String, password: String): Option[AccountId]
}
