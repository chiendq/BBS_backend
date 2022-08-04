package domain.post.valueObjects

import domain.common.valueObjects.ValueObject

case class Title(value: String) extends ValueObject {
  if (value.length > 150) throw new IllegalArgumentException("Title can not over 150 characters")
  if (value.isEmpty) throw new IllegalArgumentException("Title can not left blank")
}
