package trart.kr.tartaitest3.order.domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrderStatusTest {
    // step by step
    @Test
    fun `PENDING to PAID is allowed`() {
        assertTrue(OrderStatus.PENDING.canTransitionTo(OrderStatus.PAID))
    }

    @Test
    fun `PAID to PREPARING is allowed`() {
        assertTrue(OrderStatus.PAID.canTransitionTo(OrderStatus.PREPARING))
    }

    @Test
    fun `PREPARING to SHIPPED is allowed`() {
        assertTrue(OrderStatus.PREPARING.canTransitionTo(OrderStatus.SHIPPED))
    }

    @Test
    fun `SHIPPED to DELIVERED is allowed`() {
        assertTrue(OrderStatus.SHIPPED.canTransitionTo(OrderStatus.DELIVERED))
    }

    // skip steps
    @Test
    fun `PENDING to SHIPPED is allowed`() {
        assertTrue(OrderStatus.PENDING.canTransitionTo(OrderStatus.SHIPPED))
    }

    @Test
    fun `PAID to DELIVERED is allowed`() {
        assertTrue(OrderStatus.PAID.canTransitionTo(OrderStatus.DELIVERED))
    }

    // cancel from any status
    @Test
    fun `PENDING to CANCELLED is allowed`() {
        assertTrue(OrderStatus.PENDING.canTransitionTo(OrderStatus.CANCELLED))
    }

    @Test
    fun `SHIPPED to CANCELLED is allowed`() {
        assertTrue(OrderStatus.SHIPPED.canTransitionTo(OrderStatus.CANCELLED))
    }

    // going back is not allowed
    @Test
    fun `PAID to PENDING is not allowed`() {
        assertFalse(OrderStatus.PAID.canTransitionTo(OrderStatus.PENDING))
    }

    @Test
    fun `SHIPPED to PREPARING is not allowed`() {
        assertFalse(OrderStatus.SHIPPED.canTransitionTo(OrderStatus.PREPARING))
    }

    @Test
    fun `DELIVERED to SHIPPED is not allowed`() {
        assertFalse(OrderStatus.DELIVERED.canTransitionTo(OrderStatus.SHIPPED))
    }

    // no transition from terminal status
    @Test
    fun `DELIVERED cannot transition to any status`() {
        OrderStatus.entries.forEach { next ->
            assertFalse(OrderStatus.DELIVERED.canTransitionTo(next))
        }
    }

    @Test
    fun `CANCELLED cannot transition to any status`() {
        OrderStatus.entries.forEach { next ->
            assertFalse(OrderStatus.CANCELLED.canTransitionTo(next))
        }
    }
}
