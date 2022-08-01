package domain.post.dtos

case class PostDTO(id: String,
                   title: String,
                   content: String,
                   authorName: String,
                   createdAt: String,
                   updatedOn: String,
                   thumbnail: String)

