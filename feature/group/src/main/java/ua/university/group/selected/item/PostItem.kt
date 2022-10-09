package ua.university.group.selected.item

import androidx.compose.ui.graphics.Color

internal data class PostItem(
    val creatorName: String,
    val creatorColor: Color,
    val creationDate: String,
    val content: String
)

internal fun previewPostItem(key: Int = 8, color: Color = Color.Magenta): PostItem {
    return PostItem(
        creatorName = "Petro Ivanov",
        creatorColor = color,
        creationDate = "$key/10/2022",
        content = "Short content about beautiful world and programming!",
    )
}

internal fun previewPostItemList(size: Int = 6): List<PostItem> {
    val posts = mutableListOf<PostItem>()
    val colors = listOf(Color.Blue, Color.Magenta, Color.DarkGray)
    repeat(size) { posts.add(previewPostItem(key = it + 1, color = colors.random())) }
    return posts
}