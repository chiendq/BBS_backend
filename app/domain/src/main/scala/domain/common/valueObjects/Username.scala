package domain.common.valueObjects

case class Username(value: String){
  if (value.length > 50) throw new IllegalArgumentException("Username too long")
  if (value.isEmpty) throw new IllegalArgumentException("Username can not left blank")
}
