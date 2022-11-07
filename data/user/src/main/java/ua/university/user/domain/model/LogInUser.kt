package ua.university.user.domain.model

data class LogInUser(
    val email: String,
    val password: String
)

fun previewLogInUser(key: Int = 22): LogInUser {
    return LogInUser(
        email = "petro.ivanov@gmail.com",
        password = "Pass12abc$key!",
    )
}