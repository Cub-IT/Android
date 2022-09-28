package ua.university.group.remote.entry

data class GetGroupPostsResponseItem (
    val id: String,
    val creationDate: String,
    val editDate: String,
    val description: String
)