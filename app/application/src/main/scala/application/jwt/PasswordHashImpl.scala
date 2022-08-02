package application.jwt

import com.github.t3hnar.bcrypt._
import domain.auth.PasswordHash
import domain.common.valueObjects.{Password, RawPassword}

object PasswordHashImpl extends PasswordHash{
  override def make(password: String): String = {
    password.bcryptBounded(generateSalt)
  }

  override def verify(password: RawPassword, hashed: Password): Boolean = {
    password.value.isBcryptedBounded(hashed.value)
  }
}
