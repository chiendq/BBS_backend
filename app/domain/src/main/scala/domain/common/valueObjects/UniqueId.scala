package domain.common.valueObjects

import domain.common.Identifier

case class UniqueId(value: String) extends Identifier[String]{
  if(value.length != 36) throw new IllegalArgumentException("Invalid Id length")
}
