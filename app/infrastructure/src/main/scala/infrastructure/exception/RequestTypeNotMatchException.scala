package infrastructure.mySqlDao.exception

case class RequestTypeNotMatchException(message: String) extends Throwable(message)
