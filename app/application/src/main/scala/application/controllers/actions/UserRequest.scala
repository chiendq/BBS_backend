package application.controllers.actions

import domain.common.valueObjects.UniqueId
import play.api.mvc.{Request, WrappedRequest}

case class UserRequest[A](accountId: UniqueId, request: Request[A]) extends WrappedRequest[A](request)
