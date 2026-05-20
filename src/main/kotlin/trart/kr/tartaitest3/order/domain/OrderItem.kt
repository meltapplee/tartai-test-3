package trart.kr.tartaitest3.order.domain

import jakarta.persistence.*

@Entity
@Table(
    name = "order_items",
    indexes = [Index(name = "idx_order_items_order_id", columnList = "order_id")],
)
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val itemName: String,

    val quantity: Int,

    val unitPrice: Int,

    val totalPrice: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,
)
