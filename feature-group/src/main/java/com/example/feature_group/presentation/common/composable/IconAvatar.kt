package com.example.feature_group.presentation.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconAvatar(
    color: Color,
    size: Dp
) {
    Icon(
        imageVector = Icons.Rounded.Person,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier
            .background(color = color, shape = CircleShape)
            .size(size = size)
    )
}

@Preview
@Composable
fun IconAvatarPreview() {
    IconAvatar(color = Color.Blue, size = 48.dp)
}