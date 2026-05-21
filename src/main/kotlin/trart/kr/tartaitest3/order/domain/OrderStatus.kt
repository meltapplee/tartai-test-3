package trart.kr.tartaitest3.order.domain

enum class OrderStatus(
    val displayName: String,
    val step: Int,
) {
    PENDING("결제 대기", 0),
    PAID("결제 완료", 1),
    PREPARING("상품 준비 중", 2),
    SHIPPED("배송 중", 3),
    DELIVERED("배송 완료", 4),
    CANCELLED("취소됨", -1),
    ;

    fun canTransitionTo(next: OrderStatus): Boolean {
        if (this == DELIVERED || this == CANCELLED) return false
        if (next == CANCELLED) return true
        return next.step > this.step
    }
}
