package domain.account.serivces

import domain.account.models.Account
import domain.common.valueObjects.{Email, RawPassword, UniqueId, Username}

import scala.util.Try

trait AccountService {
  def findByEmail(email: Email): Option[Account]

  def save(email: Email, username: Username, password: RawPassword): Try[UniqueId]
}
