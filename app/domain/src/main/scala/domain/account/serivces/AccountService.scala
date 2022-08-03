package domain.account.serivces

import domain.account.models.{Account, AccountId}
import domain.common.valueObjects.{Email, RawPassword, Username}

import scala.util.Try

trait AccountService {
  def findByEmail(email: Email): Option[Account]

  def save(email: Email, username: Username, password: RawPassword): Try[AccountId]
}
