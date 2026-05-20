package trart.kr.tartaitest3.post.controller.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostRequest(
    @field:NotBlank
    @field:Size(min = 1, max = 100)
    val title: String,
    @field:NotBlank
    @field:Size(min = 1, max = 10000)
    val content: String,
)
