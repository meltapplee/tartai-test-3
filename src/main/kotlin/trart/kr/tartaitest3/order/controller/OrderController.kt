package trart.kr.tartaitest3.order.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import trart.kr.tartaitest3.common.PageResponse
import trart.kr.tartaitest3.order.controller.dto.request.UpdateOrderStatusRequest
import trart.kr.tartaitest3.order.controller.dto.response.OrderDetailResponse
import trart.kr.tartaitest3.order.controller.dto.response.OrderSummaryResponse
import trart.kr.tartaitest3.order.service.OrderService

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
) {
    @GetMapping
    fun getOrders(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): PageResponse<OrderSummaryResponse> = orderService.getOrders(page, size)

    @GetMapping("/{orderId}")
    fun getOrder(
        @PathVariable orderId: String,
    ): OrderDetailResponse = orderService.getOrder(orderId)

    @PatchMapping("/{orderId}/status")
    fun updateStatus(
        @PathVariable orderId: String,
        @RequestBody request: UpdateOrderStatusRequest,
    ): OrderDetailResponse = orderService.updateStatus(orderId, request.status)
}
