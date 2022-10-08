package ua.university.ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconAvatar(
    color: Color,
    size: Dp,
    imageVector: ImageVector = Icons.Filled.AccountCircle
) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        tint = color,
        modifier = Modifier.size(size = size)
    )
}

@Preview
@Composable
fun IconAvatarPreview() {
    IconAvatar(color = Color.Blue, size = 48.dp)
}