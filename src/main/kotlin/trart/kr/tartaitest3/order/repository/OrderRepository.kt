package trart.kr.tartaitest3.order.repository

import org.springframework.data.jpa.repository.JpaRepository
import trart.kr.tartaitest3.order.domain.Order

interface OrderRepository : JpaRepository<Order, String>
