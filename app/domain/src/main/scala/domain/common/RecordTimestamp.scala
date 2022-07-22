package domain.common

import org.joda.time.DateTime

trait RecordTimestamp {
  val createdOn: Option[DateTime] = None
  val updatedOn: Option[DateTime] = None
}
