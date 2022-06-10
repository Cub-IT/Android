package com.example.feature_group.presentation.group_list.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_group.presentation.group_list.item.GroupListItem

@ExperimentalMaterialApi
@Composable
fun GroupList(
    groups: List<GroupListItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(groups) { group ->
            GroupCard(
                modifier = Modifier.padding(vertical = 4.dp),
                group = group,
                onClick = {}
            )
        }
    }
}

@Preview(backgroundColor = 1)
@ExperimentalMaterialApi
@Composable
fun GroupListPreview() {
    GroupList(
        groups = listOf(
            GroupListItem(
                name = "Group name 1",
                ownerName = "Teacher Name 1",
                coverColor = Color.Blue
            ),
            GroupListItem(
                name = "Group name 2",
                ownerName = "Teacher Name 2",
                coverColor = Color.Magenta
            ),
            GroupListItem(
                name = "Group name 3",
                ownerName = "Teacher Name 3",
                coverColor = Color.DarkGray
            ),
            GroupListItem(
                name = "Group name 4",
                ownerName = "Teacher Name 4",
                coverColor = Color.Magenta
            ),
            GroupListItem(
                name = "Group name 5",
                ownerName = "Teacher Name 5",
                coverColor = Color.DarkGray
            ),
            GroupListItem(
                name = "Group name 6",
                ownerName = "Teacher Name 6",
                coverColor = Color.Blue
            ),
            GroupListItem(
                name = "Group name 6",
                ownerName = "Teacher Name 6",
                coverColor = Color.Blue
            )
        )
    )
}