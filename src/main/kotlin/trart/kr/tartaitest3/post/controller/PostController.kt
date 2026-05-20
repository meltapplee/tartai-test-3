package trart.kr.tartaitest3.post.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import trart.kr.tartaitest3.common.PageResponse
import trart.kr.tartaitest3.post.controller.dto.request.PostRequest
import trart.kr.tartaitest3.post.controller.dto.response.PostDetailResponse
import trart.kr.tartaitest3.post.controller.dto.response.PostSummaryResponse
import trart.kr.tartaitest3.post.service.PostService

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @Valid @RequestBody request: PostRequest,
    ) = postService.createPost(request)

    @GetMapping("/{postId}")
    fun getPost(
        @PathVariable postId: Long,
    ): PostDetailResponse = postService.getPost(postId)

    @GetMapping
    fun getPosts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): PageResponse<PostSummaryResponse> = postService.getPosts(page, size)

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable postId: Long,
    ) = postService.deletePost(postId)
}
