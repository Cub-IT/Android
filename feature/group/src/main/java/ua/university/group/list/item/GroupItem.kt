package ua.university.group.list.item

import androidx.compose.ui.graphics.Color

internal data class GroupItem(
    val id: String,
    val name: String,
    val description: String,
    val ownerName: String,
    val coverColor: Color,
)
/*
internal fun Group.toGroupItem(): GroupItem {
    return GroupItem(id.toString(), title, description, ownerId.toString(), color)
}

internal fun previewGroupItem(key: Int = 22, color: Color = Color.Blue): GroupItem {
    return previewGroup(key = key, color = color).toGroupItem()
}

internal fun previewGroupItemList(size: Int = 6): List<GroupItem> {
    return previewGroupList(size = size).map { it.toGroupItem() }
}*/