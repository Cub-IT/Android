package com.example.feature_group.presentation.group.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_group.presentation.group.item.GroupItem

@Composable
fun GroupHeaderCard(
    modifier: Modifier = Modifier,
    group: GroupItem
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = group.coverColor,
        contentColor = Color.White,
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = group.name,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupHeaderCardPreview() {
    GroupHeaderCard(
        group = GroupItem(
            name = "Group name",
            coverColor = Color.Blue
        ),
        modifier = Modifier.padding(8.dp)
    )
}