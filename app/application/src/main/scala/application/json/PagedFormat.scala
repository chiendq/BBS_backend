package application.json

import domain.common.Paged
import play.api.libs.json.{JsValue, Json, Writes}

object PagedFormat {
  implicit def pagedFormats[T](implicit writes: Writes[T]): Writes[Paged[T]] = Writes { page =>
    Json.obj(
      "data" -> Json.toJson(page.items),
      "total_page" -> page.totalPage,
      "count" -> page.count,
      "page" -> page.page,
      "page_size" -> page.pageSize,
    )
  }
}
