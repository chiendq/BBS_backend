package domain.post.valueObjects

import domain.common.valueObjects.ValueObject

case class AuthorName(value: String) extends ValueObject {
  if (value.length > 50) throw new IllegalArgumentException("Author name can not over 50 characters")
  if (value.isEmpty) throw new IllegalArgumentException("Author name can not left blank")
}
