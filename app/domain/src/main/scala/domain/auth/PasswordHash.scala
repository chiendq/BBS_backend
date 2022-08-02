package domain.auth

import domain.common.valueObjects.{Password, RawPassword}

trait PasswordHash {
  def make(password: String): String

  def verify(password: RawPassword, hashed: Password): Boolean
}
