package trart.kr.tartaitest3.order.controller

import org.springframework.web.bind.annotation.*
import trart.kr.tartaitest3.common.PageResponse
import trart.kr.tartaitest3.order.service.OrderService
import trart.kr.tartaitest3.order.controller.dto.response.OrderDetailResponse
import trart.kr.tartaitest3.order.controller.dto.response.OrderSummaryResponse

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping
    fun getOrders(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): PageResponse<OrderSummaryResponse> =
        orderService.getOrders(page, size)

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: String): OrderDetailResponse =
        orderService.getOrder(orderId)
}
