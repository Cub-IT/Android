package ua.university.user.domain.model

import androidx.compose.ui.graphics.Color

data class User(
    val id: Int,
    val participantIds: List<Int>,

    val firstName: String,
    val lastName: String,
    val email: String,
    val avatarUrl: String,
    val color: Color,
)

fun previewUser(key: Int = 22, color: Color = Color.Magenta): User {
    return User(
        id = key,
        participantIds = emptyList(),

        firstName = "Alex",
        lastName = "Vanyo",
        email = "petro.ivanov@gmail.com",
        avatarUrl = "https://pbs.twimg.com/profile_images/1431339735931305989/nOE2mmi2_400x400.jpg",
        color = color,
    )
}