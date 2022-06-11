package com.example.feature_group.presentation.group_list.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_group.presentation.common.item.GroupItem

@ExperimentalMaterialApi
@Composable
fun GroupCard(
    modifier: Modifier = Modifier,
    group: GroupItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = group.coverColor,
        contentColor = Color.White,
        elevation = 0.dp
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = group.name,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = group.description,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = group.ownerName,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun GroupCardPreview() {
    GroupCard(
        group = GroupItem(
            name = "Group name",
            description = "Here is a description",
            ownerName = "Teacher Name",
            coverColor = Color.Blue
        ),
        onClick = {},
        modifier = Modifier.padding(8.dp)
    )
}