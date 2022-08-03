package domain.exceptions

case class EntityNotFoundException(message: String) extends RuntimeException(message)

