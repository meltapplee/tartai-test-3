package trart.kr.tartaitest3.post.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class PostSummaryResponse(
    val id: Long,
    val title: String,
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    val postedAt: LocalDateTime,
)
