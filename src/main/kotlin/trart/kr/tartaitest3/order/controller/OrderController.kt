package trart.kr.tartaitest3.order.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "주문 API", description = "주문 조회 및 상태 관리 API")
class OrderController(
    private val orderService: OrderService,
) {
    @Operation(summary = "주문 목록 조회", description = "주문 목록을 페이지 단위로 조회합니다.")
    @GetMapping
    fun getOrders(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): PageResponse<OrderSummaryResponse> = orderService.getOrders(page, size)

    @Operation(summary = "주문 상세 조회", description = "주문 ID로 특정 주문의 상세 정보를 조회합니다.")
    @GetMapping("/{orderId}")
    fun getOrder(
        @PathVariable orderId: String,
    ): OrderDetailResponse = orderService.getOrder(orderId)

    @Operation(summary = "주문 상태 변경", description = "주문 상태를 변경합니다. 변경 시 SSE 구독 중인 클라이언트에게 이벤트가 전송됩니다.")
    @PatchMapping("/{orderId}/status")
    fun updateStatus(
        @PathVariable orderId: String,
        @RequestBody request: UpdateOrderStatusRequest,
    ): OrderDetailResponse = orderService.updateStatus(orderId, request.status)
}
