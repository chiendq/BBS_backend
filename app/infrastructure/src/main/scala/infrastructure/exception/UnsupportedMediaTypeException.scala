package infrastructure.exception

case class UnsupportedMediaTypeException(message: String) extends RuntimeException(message)
