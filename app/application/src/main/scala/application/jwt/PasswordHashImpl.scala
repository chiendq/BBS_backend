package application.jwt

import com.github.t3hnar.bcrypt._
import domain.auth.PasswordHash

object PasswordHashImpl extends PasswordHash{
  override def make(password: String): String = {
    password.bcryptBounded(generateSalt)
  }

  override def verify(password: String, hashed: String): Boolean = {
    password.isBcryptedBounded(hashed)
  }
}
