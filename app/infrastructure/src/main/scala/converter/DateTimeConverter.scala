package converter

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateTimeConverter {
  def toYyyyMmDdString(value: DateTime): String = {
    DateTimeFormat.forPattern("YYYY/MM/dd").print(value)
  }
}