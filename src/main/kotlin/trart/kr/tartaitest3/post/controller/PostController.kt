package trart.kr.tartaitest3.post.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import trart.kr.tartaitest3.common.PageResponse
import trart.kr.tartaitest3.post.service.PostService
import trart.kr.tartaitest3.post.controller.dto.request.PostCreateRequest
import trart.kr.tartaitest3.post.controller.dto.response.PostDetailResponse
import trart.kr.tartaitest3.post.controller.dto.response.PostSummaryResponse

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@Valid @RequestBody request: PostCreateRequest) =
        postService.createPost(request)

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long): PostDetailResponse =
        postService.getPost(postId)

    @GetMapping
    fun getPosts(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): PageResponse<PostSummaryResponse> =
        postService.getPosts(page, size)

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable postId: Long) =
        postService.deletePost(postId)
}
