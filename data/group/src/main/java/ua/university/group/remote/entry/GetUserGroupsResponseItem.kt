package ua.university.group.remote.entry

import androidx.compose.ui.graphics.Color
import ua.university.group.Group

data class GetUserGroupsResponseItem(
    val id: String,
    val title: String,
    val description: String,
    val code: String,
    val creationDate: String,
    val ownerFirstName: String,
    val ownerLastName: String
)

fun GetUserGroupsResponseItem.toGroup(): Group {
    return Group(
        id = this.id,
        name = this.title,
        description = this.description,
        ownerName = "${this.ownerFirstName} ${this.ownerLastName}",
        coverColor = Color(0xFF3B79E8) //TODO: set real color
    )
}
