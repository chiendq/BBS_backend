package infrastructure.mySqlDao

import domain.account.models.Account
import domain.post.models.{Post, PostId}
import scalikejdbc.WrappedResultSet
import skinny.orm.{Alias, SkinnyCRUDMapperWithId}

import javax.inject.Singleton

@Singleton
object PostDao extends SkinnyCRUDMapperWithId[PostId, Post] {

  override lazy val tableName = "post"

  override def defaultAlias: Alias[Post] = createAlias("p")

  belongsToWithAlias[Account](AccountDao -> AccountDao.createAlias("account"), (p, a) => p.copy(account = a)).byDefault

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Post]): Post =
    Post(
      id = PostId(rs.get(n.id)),
      title = rs.get(n.title),
      content = rs.get(n.content),
      authorName = rs.get(n.authorName),
      createdAt = rs.get(n.createdAt),
      updatedOn = rs.get(n.updatedOn),
      thumbnail = rs.get(n.thumbnail))

  override def idToRawValue(id: PostId): Any = id.value

  override def rawValueToId(value: Any): PostId = PostId(value.toString)
}
