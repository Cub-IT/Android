package ua.university.group.selected.screen.composable

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.core.text.util.LinkifyCompat
import ua.university.group.selected.item.PostItem
import ua.university.group.selected.item.previewPostItem
import ua.university.ui.composable.IconAvatar
import ua.university.ui.theme.Typography

@Composable
internal fun PostCard(
    post: PostItem,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconAvatar(color = post.creatorColor, size = 40.dp)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = post.creatorName,
                        style = Typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = post.creationDate,
                        style = Typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AndroidView(
                factory = { context ->
                    val textView = TextView(context)

                    textView.text = post.content
                    textView.textSize = 16F
                    textView.setTextColor("#000000".toColorInt())
                    LinkifyCompat.addLinks(textView, Linkify.ALL)
                    textView.movementMethod = LinkMovementMethod.getInstance()

                    textView
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun TaskPreview() {
    PostCard(
        post = previewPostItem(),
        modifier = Modifier.padding(10.dp)
    )
}