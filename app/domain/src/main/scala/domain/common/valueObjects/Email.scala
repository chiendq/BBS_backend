package domain.common.valueObjects

case class Email private(value: String){
//    if (! value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) throw new IllegalArgumentException("Email is not valid")
}
