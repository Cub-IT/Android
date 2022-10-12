package ua.university.group.selected.item

import androidx.compose.ui.graphics.Color

internal data class PostItem(
    val id: String,
    val creatorName: String,
    val creatorColor: Color,
    val creationDate: String,
    val title: String,
    val content: String,
)

internal fun previewPostItem(key: Int = 8, color: Color = Color.Magenta): PostItem {
    return PostItem(
        id = "$key",
        creatorName = "Petro Ivanov",
        creatorColor = color,
        creationDate = "$key/10/2022",
        title = "Example post title. And more detailed description of it. Open the post to see more!",
        content = "Short content about beautiful world and programming! There is more information, so open the post, please.",
    )
}

internal fun previewPostItemList(size: Int = 6): List<PostItem> {
    val posts = mutableListOf<PostItem>()
    val colors = listOf(Color.Blue, Color.Magenta, Color.DarkGray)
    repeat(size) { posts.add(previewPostItem(key = it + 1, color = colors.random())) }
    return posts
}