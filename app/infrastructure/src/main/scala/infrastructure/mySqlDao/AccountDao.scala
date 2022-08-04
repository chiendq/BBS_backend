package infrastructure.mySqlDao

import domain.account.models.Account
import domain.common.valueObjects.{Email, HashedPassword, RawPassword, UniqueId, Username}
import scalikejdbc.WrappedResultSet
import skinny.orm.{Alias, SkinnyCRUDMapperWithId}

object AccountDao extends SkinnyCRUDMapperWithId[UniqueId, Account] {
  override lazy val tableName = "account"

  override def defaultAlias: Alias[Account] = createAlias("a")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Account]): Account =
    Account(
      id = UniqueId(rs.get(n.id)),
      username = Username(rs.get(n.username)),
      email = Email(rs.get(n.email)),
      password = HashedPassword(rs.get(n.password)))

  override def idToRawValue(id: UniqueId): Any = id.value

  override def rawValueToId(value: Any): UniqueId = UniqueId(value.toString)
}
