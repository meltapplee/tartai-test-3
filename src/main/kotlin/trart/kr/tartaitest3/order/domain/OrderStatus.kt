package trart.kr.tartaitest3.order.domain

enum class OrderStatus(
    val displayName: String,
) {
    PENDING("결제 대기"),
    PAID("결제 완료"),
    PREPARING("상품 준비 중"),
    SHIPPED("배송 중"),
    DELIVERED("배송 완료"),
    CANCELLED("취소됨"),
}
