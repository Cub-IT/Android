package com.example.feature_group.presentation.group.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_group.presentation.common.composable.IconAvatar
import com.example.feature_group.presentation.group.item.PostItem

@ExperimentalMaterialApi
@Composable
fun Task(
    task: PostItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconAvatar(color = task.creatorColor, size = 48.dp)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = task.creatorName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = task.creationDate,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = task.content)
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    Task(
        task = PostItem(
            creatorName = "Mark Yavorskiy",
            creatorColor = Color.Blue,
            creationDate = "May 16",
            content = "Запрошую Вас на Зум-конференцію."
        ),
        modifier = Modifier.padding(10.dp)
    )
}