package ua.university.post.domain.model

data class NewPost(
    val groupId: String,
    val memberId: String,
    val title: String,
    val content: String,
)