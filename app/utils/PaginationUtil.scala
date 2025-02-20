package utils

object PaginationUtil {

  def paginate[T](items: Seq[T], page: Int, pageSize: Int): Seq[T] = {
    val from = (page - 1) * pageSize
    val to = from + pageSize
    items.slice(from, to)
  }
}
