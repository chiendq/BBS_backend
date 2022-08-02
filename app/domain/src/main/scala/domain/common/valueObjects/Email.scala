package domain.common.valueObjects

case class Email(value: String){
    require(value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"), "Incorrect Email")
}
