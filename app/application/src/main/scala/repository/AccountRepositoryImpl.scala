package repository

import account.{AccountId, AccountRepository}
import mySqlDao.AccountDao

class AccountRepositoryImpl extends AccountRepository{
  import mySqlDao.AccountDao._
  override def save(email: String, username: String, password: String): Option[AccountId] =
    Some(
      AccountDao.createWithNamedValues(
        column.id  -> 1L,
        column.email -> email,
        column.username -> username,
        column.password -> password
      )
    )
}
