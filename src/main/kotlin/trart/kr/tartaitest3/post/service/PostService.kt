package trart.kr.tartaitest3.post.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trart.kr.tartaitest3.common.PageResponse
import trart.kr.tartaitest3.post.controller.dto.request.PostRequest
import trart.kr.tartaitest3.post.controller.dto.response.PostDetailResponse
import trart.kr.tartaitest3.post.controller.dto.response.PostSummaryResponse
import trart.kr.tartaitest3.post.domain.Post
import trart.kr.tartaitest3.post.repository.PostRepository

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun createPost(request: PostRequest) {
        postRepository.save(Post(title = request.title, content = request.content))
    }

    fun getPost(id: Long): PostDetailResponse {
        val post =
            postRepository.findByIdOrNull(id)
                ?: throw NoSuchElementException("Post not found: $id")
        return post.toDetailResponse()
    }

    fun getPosts(
        page: Int,
        size: Int,
    ): PageResponse<PostSummaryResponse> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))
        val posts = postRepository.findAll(pageable)
        return PageResponse(
            content = posts.content.map { it.toSummaryResponse() },
            totalCount = posts.totalElements,
            page = page,
            size = size,
        )
    }

    @Transactional
    fun deletePost(id: Long) {
        if (!postRepository.existsById(id)) throw NoSuchElementException("Post not found: $id")
        postRepository.deleteById(id)
    }

    private fun Post.toDetailResponse() = PostDetailResponse(id, title, content, postedAt)

    private fun Post.toSummaryResponse() = PostSummaryResponse(id, title, postedAt)
}
