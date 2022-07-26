package infrastructure.mySqlDao.exception

case class EntityNotFoundException(message: String) extends RuntimeException(message)

