package trart.kr.tartaitest3.notification

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/notifications")
@Tag(name = "알림 API", description = "SSE 기반 실시간 알림 API")
class NotificationController(
    private val notificationService: NotificationService,
) {
    @Operation(summary = "주문 상태 변경 구독", description = "특정 주문의 상태 변경 이벤트를 SSE로 실시간 수신합니다.")
    @GetMapping("/subscribe/{orderId}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(
        @PathVariable orderId: String,
    ): SseEmitter = notificationService.subscribe(orderId)
}
