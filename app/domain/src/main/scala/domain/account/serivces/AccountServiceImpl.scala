package domain.account.serivces

import domain.account.AccountRepository
import domain.account.models.Account
import domain.account.valueObjects.{Email, HashedPassword, RawPassword, Username}
import domain.auth.PasswordHash
import domain.common.valueObjects.UniqueId
import domain.exceptions.account.DuplicatedException

import java.util.UUID
import javax.inject.{Inject, Singleton}
import scala.util.Try

@Singleton
class AccountServiceImpl @Inject()(
                                    passwordHash: PasswordHash,
                                    accountRepository: AccountRepository)
  extends AccountService {

  override def save(email: Email, username: Username, password: RawPassword): Try[UniqueId] = {
    if (accountRepository.isDuplicateEmail(email)) throw DuplicatedException("Email already exists")
    if (accountRepository.isExistUsername(username)) throw DuplicatedException("Username already exists")
    val uuid = UUID.randomUUID().toString
    val hashedPassword = passwordHash.make(password.value)
    val account = Account(UniqueId(uuid),
      username,
      email,
      HashedPassword(hashedPassword))
    accountRepository.save(account)
  }

  override def findByEmail(email: Email): Option[Account] = {
    accountRepository.findAccountByEmail(email)
  }
}
