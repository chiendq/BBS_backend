package application.controllers.actions


import domain.account.model.AccountId
import play.api.mvc.{Request, WrappedRequest}

case class UserRequest[A](accountId: AccountId, request: Request[A]) extends WrappedRequest[A](request)
