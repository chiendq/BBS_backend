package domain.auth

import domain.common.valueObjects.{HashedPassword, RawPassword}

trait PasswordHash {
  def make(password: String): String

  def verify(password: RawPassword, hashed: HashedPassword): Boolean
}
