package ua.university.group.selected.screen.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.group.selected.item.GroupItem
import ua.university.group.selected.item.PostItem

@Composable
internal fun PostList(
    group: GroupItem,
    posts: List<PostItem>,
    onPostClick: (groupId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        item {
            GroupHeaderCard(
                group = group,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        items(posts) { post ->
            PostCard(
                post = post,
                onClick = { onPostClick(post.id) },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}