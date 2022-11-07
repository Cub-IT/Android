package ua.university.post.selected.item

import ua.university.post.domain.model.Post
import ua.university.post.domain.model.previewPost

data class PostItem(
    val title: String,
    val content: String,
)

fun Post.toPostItem(): PostItem {
    return PostItem(title, content)
}

fun previewPostItem(key: Int = 8): PostItem {
    return previewPost(key = key).toPostItem()
}