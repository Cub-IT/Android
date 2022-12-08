package ua.university.group.ui.selected.item

import androidx.compose.ui.graphics.Color
import ua.university.group.data.entity.GroupEntity

data class GroupItem(
    val name: String,
    val description: String,
    val coverColor: Color,
    val code: String,
)

fun GroupEntity.toGroupItem(): GroupItem {
    return GroupItem(
        name = title,
        description = description,
        coverColor = Color(0xFF3B79E8),
        code = code,
    )
}

internal fun previewGroupItem(): GroupItem {
    return GroupItem(
        name = "Group name",
        description = "Here is a description",
        coverColor = Color.Blue,
        code = "1245a876",
    )
}