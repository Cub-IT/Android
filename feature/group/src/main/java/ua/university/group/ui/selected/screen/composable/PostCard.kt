package ua.university.group.ui.selected.screen.composable

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.core.text.util.LinkifyCompat
import ua.university.group.ui.selected.item.PostItem
import ua.university.group.ui.selected.item.previewPostItem
import ua.university.group.ui.selected.mvi.GroupUiEvent
import ua.university.ui.composable.IconAvatar
import ua.university.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostCard(
    post: PostItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isOwner: Boolean,
    handleEvent: (GroupUiEvent) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        //onClick = { expanded = !expanded },
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
                if (isOwner) {
                    IconButton(onClick = { handleEvent(GroupUiEvent.EditPostClicked(postId = post.id)) }) {
                        Icon(Icons.Default.Edit, contentDescription = null)
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd,
                ) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = post.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = if(!expanded) 2 else Int.MAX_VALUE,
                style = Typography.bodyLarge,
            )
            if (expanded) {
                Spacer(modifier = Modifier.padding(8.dp))
                AndroidView(
                    factory = { context ->
                        val textView = TextView(context)

                        textView.text = post.content
                        textView.textSize = 14F
                        textView.setTextColor("#000000".toColorInt())
                        LinkifyCompat.addLinks(textView, Linkify.ALL)
                        textView.movementMethod = LinkMovementMethod.getInstance()

                        textView
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun TaskPreview() {
    PostCard(
        post = previewPostItem(),
        onClick = { },
        modifier = Modifier.padding(10.dp),
        isOwner = false,
        handleEvent = { }
    )
}