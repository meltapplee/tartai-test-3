package trart.kr.tartaitest3.notification

import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import trart.kr.tartaitest3.order.domain.OrderStatus
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

@Service
class NotificationService {
    private val emitters = ConcurrentHashMap<String, CopyOnWriteArrayList<SseEmitter>>()

    fun subscribe(orderId: String): SseEmitter {
        val emitter = SseEmitter(300_000L)
        emitters.getOrPut(orderId) { CopyOnWriteArrayList() }.add(emitter)
        val remove = {
            emitters[orderId]?.remove(emitter)
            Unit
        }
        emitter.onCompletion(remove)
        emitter.onTimeout(remove)
        emitter.onError { remove() }
        return emitter
    }

    fun notify(
        orderId: String,
        status: OrderStatus,
    ) {
        val list = emitters[orderId] ?: return
        val event =
            SseEmitter
                .event()
                .name("order-status")
                .data(mapOf("orderId" to orderId, "status" to status.displayName))
        list.forEach { emitter ->
            try {
                emitter.send(event)
            } catch (e: Exception) {
                list.remove(emitter)
            }
        }
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            list.forEach { it.complete() }
            emitters.remove(orderId)
        }
    }
}
