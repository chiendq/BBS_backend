package domain.account.dtos

import domain.account.valueObjects.Username
import domain.common.valueObjects.UniqueId

case class LoginResponseDTO(accountId: UniqueId, username: Username)
