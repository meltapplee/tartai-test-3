package trart.kr.tartaitest3.post.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    @Column(length = 10000)
    val content: String,

    val postedAt: LocalDateTime = LocalDateTime.now(),
)
