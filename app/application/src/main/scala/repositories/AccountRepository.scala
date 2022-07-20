package repositories

trait AccountRepository {

  def save(account: AccountCreation): Option[AccountId]
}
