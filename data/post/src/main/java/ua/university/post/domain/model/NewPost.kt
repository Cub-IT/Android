package ua.university.post.domain.model

data class NewPost(
    val groupId: String,
    val creatorId: String,
    val content: String,
)