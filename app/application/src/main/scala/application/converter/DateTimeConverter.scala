package application.converter

import domain.post.PostConstants.DATE
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.{Format, JsString, JsSuccess, Reads, Writes}

object DateTimeConverter {

  implicit lazy val dateTimeFormat: Format[DateTime] = Format(
    Reads(candidate => JsSuccess(DateTime.parse(candidate.as[String]))),
    Writes(date => JsString(date.toString(DATE)))
  )

  def toJodaDateTimeString(value: DateTime): String = {
    DateTimeFormat.forPattern(DATE).print(value)
  }
}