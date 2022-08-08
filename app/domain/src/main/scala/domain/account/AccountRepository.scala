package domain.account

import domain.account.models.Account
import domain.account.valueObjects.{Email, Username}
import domain.common.valueObjects.UniqueId

import scala.util.Try

trait AccountRepository {
  def isExistUsername(username: Username): Boolean

  def isDuplicateEmail(email: Email): Boolean

  def save(account: Account): Try[UniqueId]

  def findAccountByEmail(email: Email): Option[Account]
}


