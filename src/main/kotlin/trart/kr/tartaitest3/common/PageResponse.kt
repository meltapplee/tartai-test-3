package trart.kr.tartaitest3.common

data class PageResponse<T>(
    val content: List<T>,
    val totalCount: Long,
    val page: Int,
    val size: Int,
)
