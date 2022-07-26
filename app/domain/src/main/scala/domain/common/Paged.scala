package domain.common

case class Paged[T](items: Seq[T], count: Long, page: Int, pageSize: Int) {
  lazy val totalPage = (count.toDouble / pageSize).ceil.toLong

  def map[U](f: T => U) = copy(items = items.map(f))
}
