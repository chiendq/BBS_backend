package application.controllers

import application.services.AccountServiceImpl
import play.api.Logger
import play.api.mvc._

import javax.inject._
import scala.util.{Failure, Success, Try}

@Singleton
class AccountController @Inject()(val controllerComponents: ControllerComponents,
                                  accountService: AccountServiceImpl) extends BaseController {

  lazy val logger: Logger = Logger(getClass)

}

