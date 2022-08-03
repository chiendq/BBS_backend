package domain.exception

case class RequestTypeNotMatchException(message: String) extends Throwable(message)
