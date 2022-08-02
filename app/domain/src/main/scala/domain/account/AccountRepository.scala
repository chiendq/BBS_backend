package domain.account

import domain.account.models.{Account, AccountId}
import domain.common.valueObjects.Email

import scala.util.Try

trait AccountRepository {
  def isExistEmail(email: Email): Boolean

  def save(account: Account): Try[AccountId]

  def findAccountByEmail(email: String): Option[Account]
}


