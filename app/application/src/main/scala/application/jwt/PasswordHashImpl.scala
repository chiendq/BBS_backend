package application.jwt

import com.github.t3hnar.bcrypt._
import domain.account.valueObjects.{HashedPassword, RawPassword}
import domain.auth.PasswordHash

object PasswordHashImpl extends PasswordHash{
  override def make(password: String): String = {
    password.bcryptBounded(generateSalt)
  }

  override def verify(password: RawPassword, hashed: HashedPassword): Boolean = {
    password.value.isBcryptedBounded(hashed.value)
  }
}
