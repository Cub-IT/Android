package ua.university.user.remote.entry

import ua.university.user.User

data class SignResponseEntry(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun SignResponseEntry.toUser(): User {
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}
