package domain.common.valueObjects

case class Email(value: String) extends AnyVal with ValueObject{
    require(value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"), "Incorrect Email")
}
