package trart.kr.tartaitest3.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import trart.kr.tartaitest3.post.domain.Post

interface PostRepository : JpaRepository<Post, Long>
