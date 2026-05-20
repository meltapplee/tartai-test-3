package trart.kr.tartaitest3.order.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class OrderDetailResponse(
    val id: String,
    val status: String,
    val items: List<OrderItemResponse>,
    val totalAmount: Int,
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    val orderedAt: LocalDateTime,
)

data class OrderItemResponse(
    val itemName: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int,
)
