package domain.exceptions.account

import domain.exceptions.post.PostException

case class DuplicatedException(message: String) extends IllegalArgumentException(message) with PostException {

}
