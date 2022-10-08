package ua.university.group.model

import androidx.compose.ui.graphics.Color

data class Group(
    val id: String,
    val name: String,
    val description: String,
    val ownerName: String,
    val coverColor: Color
)

fun previewGroup(key: Int = 22, color: Color = Color.Blue): Group {
    return Group(
        id = "123456abc$key",
        name = "Group name $key",
        description = "Here is a description",
        ownerName = "Teacher Name $key",
        coverColor = color
    )
}

fun previewGroupList(size: Int = 6): List<Group> {
    val groups = mutableListOf<Group>()
    val colors = listOf(Color.Blue, Color.Magenta, Color.DarkGray)
    repeat(size) { groups.add(previewGroup(key = it + 1, color = colors.random())) }
    return groups
}