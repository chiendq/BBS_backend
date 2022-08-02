package infrastructure.mySqlDao

import domain.account.model.{Account, AccountId}
import domain.common.valueObjects.{Email, Password, RawPassword, Username}
import scalikejdbc.WrappedResultSet
import skinny.orm.{Alias, SkinnyCRUDMapperWithId}

object AccountDao extends SkinnyCRUDMapperWithId[AccountId, Account] {
  override lazy val tableName = "account"

  override def defaultAlias: Alias[Account] = createAlias("a")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Account]): Account =
    Account(
      id = AccountId(rs.get(n.id)),
      username = Username(rs.get(n.username)),
      email = Email(rs.get(n.email)),
      password = Password(rs.get(n.password)))

  override def idToRawValue(id: AccountId): Any = id.value

  override def rawValueToId(value: Any): AccountId = AccountId(value.toString)
}
