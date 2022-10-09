package ua.university.settings.profile.item

data class UserItem(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun previewUserItem(key: Int = 22): UserItem {
    return UserItem(
        id = "123456abc$key",
        firstName = "Petro",
        lastName = "Ivanov",
        email = "petro.ivanov@gmail.com",
    )
}