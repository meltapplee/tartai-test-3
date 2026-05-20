package trart.kr.tartaitest3.order.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trart.kr.tartaitest3.common.PageResponse
import trart.kr.tartaitest3.notification.NotificationService
import trart.kr.tartaitest3.order.controller.dto.response.OrderDetailResponse
import trart.kr.tartaitest3.order.controller.dto.response.OrderItemResponse
import trart.kr.tartaitest3.order.controller.dto.response.OrderSummaryResponse
import trart.kr.tartaitest3.order.domain.Order
import trart.kr.tartaitest3.order.domain.OrderStatus
import trart.kr.tartaitest3.order.repository.OrderRepository

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val notificationService: NotificationService,
) {
    //    @Cacheable(cacheNames = ["orders"], key = "#page + ':' + #size")
    fun getOrders(
        page: Int,
        size: Int,
    ): PageResponse<OrderSummaryResponse> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "orderedAt"))
        val orders = orderRepository.findAll(pageable)
        println(orders)
        return PageResponse(
            content = orders.content.map { it.toSummaryResponse() },
            totalCount = orders.totalElements,
            page = page,
            size = size,
        )
    }

//    @Cacheable(cacheNames = ["order"], key = "#id")
    fun getOrder(id: String): OrderDetailResponse {
        val order =
            orderRepository.findByIdOrNull(id)
                ?: throw NoSuchElementException("Order not found: $id")
        return order.toDetailResponse()
    }

    private fun Order.toSummaryResponse() = OrderSummaryResponse(id, status.displayName, totalAmount, orderedAt)

    private fun Order.toDetailResponse() =
        OrderDetailResponse(
            id = id,
            status = status.displayName,
            items = items.map { OrderItemResponse(it.itemName, it.quantity, it.unitPrice, it.totalPrice) },
            totalAmount = totalAmount,
            orderedAt = orderedAt,
        )

    //    @CacheEvict(cacheNames = ["order", "orders"], allEntries = true)
    @Transactional
    fun updateStatus(
        id: String,
        status: OrderStatus,
    ): OrderDetailResponse {
        val order =
            orderRepository.findByIdOrNull(id)
                ?: throw NoSuchElementException("Order not found: $id")

        order.status = status
        notificationService.notify(id, status)
        return order.toDetailResponse()
    }
}
