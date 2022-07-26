package application.converter

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.{Format, JsString, JsSuccess, Reads, Writes}

object DateTimeConverter {
  val FORMATTER = "YYYY/MM/dd"

  implicit lazy val dateTimeFormat: Format[DateTime] = Format(
    Reads(candidate => JsSuccess(DateTime.parse(candidate.as[String]))),
    Writes(date => JsString(date.toString(FORMATTER)))
  )

  def toJodaDateTimeString(value: DateTime): String = {
    DateTimeFormat.forPattern(FORMATTER).print(value)
  }
}