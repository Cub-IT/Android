package ua.university.post.data.local

import kotlinx.datetime.LocalDateTime
import ua.university.post.domain.model.Post

data class PostEntity(
    val id: Int,
    val userId: String,
    val groupId: String,

    val title: String,
    val content: String,
    val createdDateTime: LocalDateTime,
    val updatedDateTime: LocalDateTime,
)

fun PostEntity.toPost(): Post {
    return Post(id, userId, groupId, title, content, createdDateTime, updatedDateTime)
}

fun List<PostEntity>.toPostList(): List<Post> {
    return this.map { it.toPost() }
}
