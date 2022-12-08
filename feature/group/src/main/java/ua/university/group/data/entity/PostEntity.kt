package ua.university.group.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PostEntity(
    @PrimaryKey
    val id: String,
    @SerializedName("class_id")
    val classId: String,
    val title: String,
    val description: String,
    @SerializedName("creation_date")
    val creationDate: String,
    @SerializedName("creator_first_name")
    val creatorFirstName: String,
    @SerializedName("creator_last_name")
    val creatorLastName: String,
)