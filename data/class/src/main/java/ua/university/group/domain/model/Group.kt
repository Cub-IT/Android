package ua.university.group.domain.model

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime

class Group(
    val id: Int,
    val ownerId: Int,
    val participantIds: List<Int>,
    val postIds: List<Int>,

    val title: String,
    val description: String,
    val code: String,
    val color: Color,
    val imageUrl: String,
    val createdDateTime: LocalDateTime
)

fun previewGroup(key: Int = 22, color: Color = Color.Blue): Group {
    return Group(
        id = key,
        ownerId = key * 2,
        participantIds = emptyList(),
        postIds = emptyList(),

        title = "Group name $key",
        description = "Here is a description",
        code = "je08Ur4eDwWr",
        color = color,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/now-in-android.appspot.com/o/img%2Fic_topic_Headlines.svg?alt=media&token=506faab0-617a-4668-9e63-4a2fb996603f",
        createdDateTime = LocalDateTime(2022, 1, 6, 0, 0)
    )
}

fun previewGroupList(size: Int = 6): List<Group> {
    val groups = mutableListOf<Group>()
    val colors = listOf(Color.Blue, Color.Magenta, Color.DarkGray)
    repeat(size) { groups.add(previewGroup(key = it + 1, color = colors.random())) }
    return groups
}