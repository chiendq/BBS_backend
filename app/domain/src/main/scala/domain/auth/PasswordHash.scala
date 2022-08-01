package domain.auth

trait PasswordHash {
  def make(password: String): String

  def verify(password: String, hashed: String): Boolean
}
