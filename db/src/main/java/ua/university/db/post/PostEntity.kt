package ua.university.db.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val creationDate: String,
    val editDate: String,
    val description: String
)