package domain.account

import domain.account.model.{Account, AccountId}

import scala.util.Try

trait AccountRepository {
  def isExistEmail(email: String): Boolean


  def save(account: Account): Try[AccountId]

  def findAccountByEmail(email: String): Option[Account]
}


