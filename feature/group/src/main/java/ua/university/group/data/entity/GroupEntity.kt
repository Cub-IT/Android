package ua.university.group.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GroupEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val code: String,
    @SerializedName("creation_date")
    val creationDate: String,
    val label: String,
    @SerializedName("creator_first_name")
    val creatorFirstName: String,
    @SerializedName("creator_last_name")
    val creatorLastName: String,
)
