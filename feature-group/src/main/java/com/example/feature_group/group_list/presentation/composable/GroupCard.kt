package com.example.feature_group.group_list.presentation.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature_group.group_list.presentation.item.GroupItem

@ExperimentalMaterialApi
@Composable
fun GroupCard(
    group: GroupItem
) {
    Card(
        onClick = { /*TODO*/ }
    ) {
        Row {
            Text(
                text = group.name,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = null,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun GroupCardPreview() {
    GroupCard(GroupItem("group name"))
}