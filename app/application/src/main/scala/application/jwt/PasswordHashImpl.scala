package application.jwt

import com.github.t3hnar.bcrypt._
import domain.auth.PasswordHash
import domain.common.valueObjects.{HashedPassword, RawPassword}

object PasswordHashImpl extends PasswordHash{
  override def make(password: String): String = {
    password.bcryptBounded(generateSalt)
  }

  override def verify(password: RawPassword, hashed: HashedPassword): Boolean = {
    password.value.isBcryptedBounded(hashed.value)
  }
}
