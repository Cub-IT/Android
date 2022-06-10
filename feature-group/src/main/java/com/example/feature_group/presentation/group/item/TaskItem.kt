package com.example.feature_group.presentation.group.item

import androidx.compose.ui.graphics.Color

data class TaskItem(
    val creatorName: String,
    val creatorColor: Color,
    val creationDate: String,
    val text: String
)
