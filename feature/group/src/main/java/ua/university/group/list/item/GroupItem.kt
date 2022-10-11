package ua.university.group.list.item

import androidx.compose.ui.graphics.Color
import ua.university.group.model.Group
import ua.university.group.model.previewGroup
import ua.university.group.model.previewGroupList

internal data class GroupItem(
    val id: String,
    val name: String,
    val description: String,
    val ownerName: String,
    val coverColor: Color,
)

internal fun Group.toGroupItem(): GroupItem {
    return GroupItem(id, title, description, ownerId, color)
}

internal fun previewGroupItem(key: Int = 22, color: Color = Color.Blue): GroupItem {
    return previewGroup(key = key, color = color).toGroupItem()
}

internal fun previewGroupItemList(size: Int = 6): List<GroupItem> {
    return previewGroupList(size = size).map { it.toGroupItem() }
}