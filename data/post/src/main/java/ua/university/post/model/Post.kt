package ua.university.post.model

import kotlinx.datetime.LocalDateTime

data class Post(
    val id: Int,
    val userId: String,
    val groupId: String,

    val title: String,
    val content: String,
    val createdDateTime: LocalDateTime,
    val updatedDateTime: LocalDateTime,
)

fun previewPost(key: Int = 8): Post {
    return Post(
        id = key,
        userId = "creator$key",
        groupId = "abcdef$key",

        title = "Example post title. And more detailed description of it.",
        content = "Short content about beautiful world and programming!\nFind me: http://google.com",
        createdDateTime = LocalDateTime(2022, 9, key, 6, 0),
        updatedDateTime = LocalDateTime(2022, 10, key, 6, 0),
    )
}

fun previewPostList(size: Int): List<Post> {
    val posts = mutableListOf<Post>()
    repeat(size) { posts.add(previewPost(key = it + 1)) }
    return posts
}