package ua.university.post.data.entity

import com.google.gson.annotations.SerializedName

data class PostEntity(
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