package models.post

import org.joda.time.DateTime

case class Post(id: PostId,
                title: String,
                content: String,
                authorName: String,
                createdAt: DateTime,
                updated: DateTime,
                thumbnail: String)
