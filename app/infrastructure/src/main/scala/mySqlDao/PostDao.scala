package mySqlDao

import account.{Account, AccountId}
import post.{Post, PostId}
import scalikejdbc.{TypeBinder, WrappedResultSet}
import skinny.orm.feature.AssociationsWithIdFeature
import skinny.orm.feature.associations.BelongsToAssociation
import skinny.orm.{Alias, SkinnyCRUDMapperWithId}

import java.sql.ResultSet
import mySqlDao.AccountDao
object PostDao extends SkinnyCRUDMapperWithId[PostId, Post] {

  override lazy val tableName = "post"

  override def defaultAlias: Alias[Post] = createAlias("p")


  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Post]): Post = {
    logger.info(rs.metaData)
    Post(id = PostId(rs.get(n.id)),
      title = rs.get(n.title),
      content = rs.get(n.content),
      authorName = rs.get(n.authorName),
      createdAt = rs.get(n.createdAt),
      updatedOn = rs.get(n.updatedOn),
      thumbnail = rs.get(n.thumbnail))
  }

  override def idToRawValue(id: PostId): Any = id.value

  override def rawValueToId(value: Any): PostId = PostId(value.toString.toLong)

  belongsToWithAlias[Account](AccountDao -> AccountDao.createAlias("account"),(p, a) => p.copy(account = a)).byDefault

}
