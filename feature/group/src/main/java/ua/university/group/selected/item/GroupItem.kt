package ua.university.group.selected.item

import androidx.compose.ui.graphics.Color

internal data class GroupItem(
    val name: String,
    val description: String,
    val coverColor: Color
)

internal fun previewGroupItem(): GroupItem {
    return GroupItem(
        name = "Group name",
        description = "Here is a description",
        coverColor = Color.Blue
    )
}