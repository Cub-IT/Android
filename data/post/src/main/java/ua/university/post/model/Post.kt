package ua.university.post.model

data class Post(
    val id: String,
    val groupId: String,
    val creationDate: String,
    val editDate: String,
    val description: String
)
