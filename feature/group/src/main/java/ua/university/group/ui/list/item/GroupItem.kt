package ua.university.group.ui.list.item

import androidx.compose.ui.graphics.Color
import ua.university.group.data.entity.GroupEntity

data class GroupItem(
    val id: String,
    val name: String,
    val description: String,
    val ownerName: String,
    val coverColor: Color,
)

fun GroupEntity.toGroupItem(): GroupItem {
    return GroupItem(
        id = id,
        name = title,
        description = description,
        ownerName = "$creatorFirstName $creatorLastName",
        coverColor = Color(0xFF3B79E8),
    )
}