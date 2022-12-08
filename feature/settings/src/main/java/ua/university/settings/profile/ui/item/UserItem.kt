package ua.university.settings.profile.ui.item

import ua.university.preferences.UserSP

data class UserItem(
    val firstName: String,
    val lastName: String,
    val email: String
)

fun UserSP.toUserItem(): UserItem {
    return UserItem(firstName, lastName, email)
}

fun previewUserItem(key: Int = 22): UserItem {
    return UserItem(
        firstName = "Petro",
        lastName = "Ivanov",
        email = "petro.ivanov@gmail.com",
    )
}