package domain.account

import domain.account.model.AccountId

trait AccountRepository {

  def save(email: String, username: String, password: String): Option[AccountId]
}
