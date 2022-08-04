package domain.account

import domain.account.models.Account
import domain.common.valueObjects.{Email, UniqueId}

import scala.util.Try

trait AccountRepository {
  def isDuplicateEmail(email: Email): Boolean

  def save(account: Account): Try[UniqueId]

  def findAccountByEmail(email: Email): Option[Account]
}


