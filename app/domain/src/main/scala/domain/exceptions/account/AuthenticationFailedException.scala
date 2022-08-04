package domain.exceptions.account

case class AuthenticationFailedException(message: String) extends RuntimeException(message) with AccountException
