package ua.university.post.model

import androidx.compose.ui.graphics.Color

data class Post(
    val id: String,
    val groupId: String,
    val creatorId: String,
    val creatorColor: Color,
    val creationDate: String,
    val editDate: String,
    val content: String
)

fun previewPost(key: Int = 8, color: Color = Color.Magenta): Post {
    return Post(
        id = "$key",
        groupId = "abcdef$key",
        creatorId = "creator$key",
        creatorColor = color,
        creationDate = "$key/9/2022",
        editDate = "$key/10/2022",
        content = "Short content about beautiful world and programming!",
    )
}

fun previewPostList(size: Int): List<Post> {
    val posts = mutableListOf<Post>()
    val colors = listOf(Color.Blue, Color.Magenta, Color.DarkGray)
    repeat(size) { posts.add(previewPost(key = it + 1, color = colors.random())) }
    return posts
}