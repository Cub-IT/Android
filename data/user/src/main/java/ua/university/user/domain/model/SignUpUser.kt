package ua.university.user.domain.model

data class SignUpUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

fun previewSignUpUser(key: Int = 22): SignUpUser {
    return SignUpUser(
        firstName = "Petro",
        lastName = "Ivanov",
        email = "petro.ivanov@gmail.com",
        password = "Pass12abc$key!",
    )
}