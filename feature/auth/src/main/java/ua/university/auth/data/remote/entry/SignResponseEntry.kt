package ua.university.auth.data.remote.entry

import ua.university.auth.data.local.UserItem

data class SignResponseEntry(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun SignResponseEntry.toUserItem(): UserItem {
    return UserItem(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}