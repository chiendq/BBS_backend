package domain.post.dtos

case class PaginatedPostListDTO(pageCount: Int, posts: Seq[PostDTO])