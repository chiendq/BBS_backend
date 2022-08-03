package domain.exceptions.account

import domain.exceptions.post.PostException

case class DuplicatedEmailException(message: String) extends RuntimeException(message) with PostException {

}
