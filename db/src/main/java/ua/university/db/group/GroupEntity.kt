package ua.university.db.group

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupEntity(
    val code: String,
    val color: String,
    val creationDate: String,
    val description: String,
    @PrimaryKey
    val id: String,
    val ownerColor: String,
    val ownerFirstName: String,
    val ownerId: String,
    val ownerLastName: String,
    val title: String,
)