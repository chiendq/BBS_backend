package domain.common.valueObjects

case class AuthorName(value: String) extends ValueObject {
  if(value.length > 50) throw new IllegalArgumentException("Author name can not over 50 characters")
}
