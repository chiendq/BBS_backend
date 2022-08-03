package domain.exception

case class AuthenticationFailedException(message: String) extends RuntimeException(message)
