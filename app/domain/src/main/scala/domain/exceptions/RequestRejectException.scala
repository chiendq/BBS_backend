package domain.exceptions

case class RequestRejectException(message: String = "Reject") extends RuntimeException(message){

}
