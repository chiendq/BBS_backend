package domain.account

import domain.account.model.{Account, AccountId}

import scala.util.Try

trait AccountService {
  def findByEmail(email: String): Option[Account]

  def save(email: String, username: String, password: String): Try[AccountId]
}
