package ua.university.post.model

data class NewPost(
    val groupId: String,
    val creatorId: String,
    val content: String,
)