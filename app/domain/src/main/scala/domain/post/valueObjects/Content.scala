package domain.post.valueObjects

import domain.common.valueObjects.ValueObject

case class Content(value: String) extends ValueObject {
  if (value.isEmpty) throw new IllegalArgumentException("Content can not left blank")
}
