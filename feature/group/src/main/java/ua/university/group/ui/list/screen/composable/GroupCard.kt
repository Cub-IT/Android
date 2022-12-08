package ua.university.group.ui.list.screen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.university.group.ui.list.item.GroupItem
import ua.university.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GroupCard(
    group: GroupItem,
    modifier: Modifier = Modifier,
    onClick: (groupId: String) -> Unit,
) {
    Card(
        onClick = { onClick(group.id) },
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp),
        colors = CardDefaults.cardColors(
            containerColor = group.coverColor,
            contentColor = Color.White
        )
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = group.name,
                style = Typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = group.description,
                style = Typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = group.ownerName,
                style = Typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
internal fun GroupCardPreview() {
    GroupCard(
        group = previewGroupItem(15),
        onClick = {},
        modifier = Modifier.padding(8.dp)
    )
}*/