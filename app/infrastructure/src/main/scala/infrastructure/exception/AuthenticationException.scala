package infrastructure.exception

case class AuthenticationException(message: String) extends RuntimeException(message)
