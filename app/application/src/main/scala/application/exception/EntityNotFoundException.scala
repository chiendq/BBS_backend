package application.exception

case class EntityNotFoundException(message: String) extends RuntimeException(message)

