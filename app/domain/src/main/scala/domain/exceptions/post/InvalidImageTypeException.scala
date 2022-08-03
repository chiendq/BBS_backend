package domain.exceptions.post

case class InvalidImageTypeException(message: String) extends RuntimeException(message) with PostException
