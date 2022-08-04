package domain.account.serivces

import domain.account.models.Account
import domain.account.valueObjects.{Email, RawPassword, Username}
import domain.common.valueObjects.UniqueId

import scala.util.Try

trait AccountService {
  def findByEmail(email: Email): Option[Account]

  def save(email: Email, username: Username, password: RawPassword): Try[UniqueId]
}
