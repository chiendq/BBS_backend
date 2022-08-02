package domain.account.dtos

import domain.account.model.AccountId
import domain.common.valueObjects.Username

case class LoginResponseDTO(accountId: AccountId, username: Username)
