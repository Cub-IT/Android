package ua.university.user.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun previewUser(key: Int = 22): User {
    return User(
        id = "123456abc$key",
        firstName = "Petro",
        lastName = "Ivanov",
        email = "petro.ivanov@gmail.com",
    )
}