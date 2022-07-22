package application.repository

import domain.account.AccountRepository
import domain.account.model.AccountId
import infrastructure.mySqlDao.AccountDao

class AccountRepositoryImpl extends AccountRepository{
  override def save(email: String, username: String, password: String): Option[AccountId] = ???
//    Some(
//      AccountDao.createWithNamedValues(
//        column.id  -> 1L,
//        column.email -> email,
//        column.username -> username,
//        column.password -> password
//      )
//    )
}
