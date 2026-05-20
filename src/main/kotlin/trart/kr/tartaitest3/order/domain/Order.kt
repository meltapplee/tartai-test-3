package trart.kr.tartaitest3.order.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(
    name = "orders",
    indexes = [Index(name = "idx_orders_ordered_at", columnList = "ordered_at")],
)
class Order(
    @Id
    val id: String = UUID.randomUUID().toString(),

    @Enumerated(EnumType.STRING)
    val status: OrderStatus,

    val totalAmount: Int,

    val orderedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val items: MutableList<OrderItem> = mutableListOf(),
)
