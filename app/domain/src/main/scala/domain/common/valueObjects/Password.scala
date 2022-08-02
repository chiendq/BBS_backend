package domain.common.valueObjects

case class Password(value: String){
  require(value.length > 8, "Password is too short")
  require(value.length < 20, "Password is too long")
}
