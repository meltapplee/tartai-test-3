package trart.kr.tartaitest3.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
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
    var status: OrderStatus,
    val totalAmount: Int,
    val orderedAt: LocalDateTime = LocalDateTime.now(),
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val items: MutableList<OrderItem> = mutableListOf(),
)
