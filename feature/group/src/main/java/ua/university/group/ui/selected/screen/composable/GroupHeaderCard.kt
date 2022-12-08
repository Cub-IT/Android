package ua.university.group.ui.selected.screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.university.group.ui.selected.item.GroupItem
import ua.university.group.ui.selected.item.previewGroupItem
import ua.university.ui.theme.Typography

@Composable
internal fun GroupHeaderCard(
    group: GroupItem,
    modifier: Modifier = Modifier,
) {
    val clipboardManager = LocalClipboardManager.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp),
        colors = CardDefaults.cardColors(
            containerColor = group.coverColor,
            contentColor = Color.White
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(end = 4.dp), contentAlignment = Alignment.CenterEnd) {
            TextButton(onClick = { clipboardManager.setText(AnnotatedString(group.code)) }) {
                Text(
                    text = group.code,
                    color = Color.White,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
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
    }
}

@Preview(showBackground = true)
@Composable
internal fun GroupHeaderCardPreview() {
    GroupHeaderCard(
        group = previewGroupItem(),
        modifier = Modifier.padding(8.dp)
    )
}