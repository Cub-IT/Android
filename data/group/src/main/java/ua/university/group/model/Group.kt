package ua.university.group.model

import androidx.compose.ui.graphics.Color

data class Group(
    val id: String,
    val title: String,
    val description: String,
    val ownerId: String,
    val imageUrl: String,
    val color: Color,
)

fun previewGroup(key: Int = 22, color: Color = Color.Blue): Group {
    return Group(
        id = "123456abc$key",
        title = "Group name $key",
        description = "Here is a description",
        ownerId = "Teacher Name $key",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/now-in-android.appspot.com/o/img%2Fic_topic_Headlines.svg?alt=media&token=506faab0-617a-4668-9e63-4a2fb996603f",
        color = color
    )
}

fun previewGroupList(size: Int = 6): List<Group> {
    val groups = mutableListOf<Group>()
    val colors = listOf(Color.Blue, Color.Magenta, Color.DarkGray)
    repeat(size) { groups.add(previewGroup(key = it + 1, color = colors.random())) }
    return groups
}