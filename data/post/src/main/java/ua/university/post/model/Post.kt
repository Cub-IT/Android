package ua.university.post.model

data class Post(
    val id: String,
    val groupId: String,
    val userId: String,
    val creationDate: String,
    val editDate: String,
    val content: String,
)

fun previewPost(key: Int = 8): Post {
    return Post(
        id = "$key",
        groupId = "abcdef$key",
        userId = "creator$key",
        creationDate = "$key/9/2022",
        editDate = "$key/10/2022",
        content = "Short content about beautiful world and programming!",
    )
}

fun previewPostList(size: Int): List<Post> {
    val posts = mutableListOf<Post>()
    repeat(size) { posts.add(previewPost(key = it + 1)) }
    return posts
}