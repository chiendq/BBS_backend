package domain.account.serivces

import domain.account.AccountRepository
import domain.account.models.{Account, AccountId}
import domain.auth.PasswordHash
import domain.common.valueObjects.{Email, HashedPassword, RawPassword, Username}
import domain.exceptions.account.DuplicatedEmailException

import java.util.UUID
import javax.inject.{Inject, Singleton}
import scala.util.Try

@Singleton
class AccountServiceImpl @Inject()(
                                    passwordHash: PasswordHash,
                                    accountRepository: AccountRepository)
  extends AccountService {

  override def save(email: Email, username: Username, password: RawPassword): Try[AccountId] = {
    if (accountRepository.isDuplicateEmail(email)) throw DuplicatedEmailException("Email already exist")
    val uuid = UUID.randomUUID().toString
    val hashedPassword = passwordHash.make(password.value)
    val account = Account(AccountId(uuid),
      username,
      email,
      HashedPassword(hashedPassword))
    accountRepository.save(account)
  }

  override def findByEmail(email: String): Option[Account] = {
    accountRepository.findAccountByEmail(email)
  }
}
