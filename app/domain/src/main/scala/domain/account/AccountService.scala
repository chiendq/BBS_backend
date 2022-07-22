package domain.account

trait AccountService {
  def isExistAccountId(accountId: String): Boolean

}
