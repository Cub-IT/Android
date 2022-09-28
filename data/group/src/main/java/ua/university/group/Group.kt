package ua.university.group

import androidx.compose.ui.graphics.Color

data class Group(
    val id: String,
    val name: String,
    val description: String,
    val ownerName: String,
    val coverColor: Color
)
