package ua.university.user.remote.entry

import ua.university.user.model.User

data class UserResponseEntry(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun UserResponseEntry.toUser(): User {
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}
