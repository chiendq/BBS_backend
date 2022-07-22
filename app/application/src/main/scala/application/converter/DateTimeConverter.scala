package application.converter

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateTimeConverter {
  val FORMATTER = "YYYY/MM/dd"

  def toJodaDateTimeString(value: DateTime): String = {
    DateTimeFormat.forPattern(FORMATTER).print(value)
  }
}