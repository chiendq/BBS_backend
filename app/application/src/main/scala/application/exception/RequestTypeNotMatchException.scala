package application.exception

case class RequestTypeNotMatchException(message: String) extends RuntimeException(message)
