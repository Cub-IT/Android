package com.example.feature_group.presentation.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterialApi
@Composable
fun Drawer() {
    val classNames = listOf(
        "Class name 1",
        "Class name 2",
        "Class name 3",
        "Class name 4",
        "Class name 5",
        "Class name 6"
    )

    Column {
        DrawerItem(
            icon = Icons.Rounded.Person,
            text = "CubIT",
            onClick = { }
        )
        Divider()
        LazyColumn {
            item {
                DrawerItem(
                    icon = Icons.Outlined.Home,
                    text = "Classes",
                    onClick = { }
                )
                Divider()
            }

            items(classNames) { className ->
                DrawerItem(
                    text = className,
                    color = Color.Blue,
                    onClick = {}
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    Drawer()
}