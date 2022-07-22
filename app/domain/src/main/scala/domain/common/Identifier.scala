package domain.common

trait Identifier[+A] extends Serializable {
  def value: A
}
