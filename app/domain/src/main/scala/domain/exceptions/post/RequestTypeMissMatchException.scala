package domain.exceptions.post

case class RequestTypeMissMatchException(message: String) extends Throwable(message)
