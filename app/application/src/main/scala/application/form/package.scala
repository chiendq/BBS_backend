package application

import domain.exceptions.post.RequestTypeMissMatchException
import play.api.data.{Form, FormBinding}
import play.api.libs.json.Json
import play.api.mvc.{AnyContent, Request, Result, Results}

package object form {
  implicit class FormValidation[T](form: Form[T]) {
    def valid(block: T => Result)(implicit request: Request[_],  formBinding: FormBinding): Result = {
      form.bindFromRequest().fold(
        throw RequestTypeMissMatchException(form.errors.head.key),
        block
      )
    }
  }
}
