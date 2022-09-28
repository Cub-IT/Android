package ua.university.user.remote.entry

data class SignUpRequestEntry(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)