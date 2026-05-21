package trart.kr.tartaitest3.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "게시글 API", description = "게시글 작성, 조회, 삭제 API")
class PostController(
    private val postService: PostService,
) {
    @Operation(summary = "게시글 작성", description = "새 게시글을 작성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @Valid @RequestBody request: PostRequest,
    ) = postService.createPost(request)

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID로 특정 게시글을 조회합니다.")
    @GetMapping("/{postId}")
    fun getPost(
        @PathVariable postId: Long,
    ): PostDetailResponse = postService.getPost(postId)

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 페이지 단위로 조회합니다.")
    @GetMapping
    fun getPosts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): PageResponse<PostSummaryResponse> = postService.getPosts(page, size)

    @Operation(summary = "게시글 삭제", description = "게시글 ID로 특정 게시글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable postId: Long,
    ) = postService.deletePost(postId)
}
