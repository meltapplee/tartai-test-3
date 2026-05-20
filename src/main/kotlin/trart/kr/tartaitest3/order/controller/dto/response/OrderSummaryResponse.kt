package trart.kr.tartaitest3.order.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class OrderSummaryResponse(
    val id: String,
    val status: String,
    val totalAmount: Int,
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    val orderedAt: LocalDateTime,
)
