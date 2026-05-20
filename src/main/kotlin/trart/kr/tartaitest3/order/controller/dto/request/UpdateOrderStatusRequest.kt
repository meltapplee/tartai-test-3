package trart.kr.tartaitest3.order.controller.dto.request

import trart.kr.tartaitest3.order.domain.OrderStatus

data class UpdateOrderStatusRequest(
    val status: OrderStatus,
)
