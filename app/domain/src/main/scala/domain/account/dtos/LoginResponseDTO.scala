package domain.account.dtos

import domain.common.valueObjects.{UniqueId, Username}

case class LoginResponseDTO(accountId: UniqueId, username: Username)
