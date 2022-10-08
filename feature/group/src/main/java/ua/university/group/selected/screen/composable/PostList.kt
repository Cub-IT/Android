package ua.university.group.selected.screen.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.group.model.Group
import ua.university.group.model.Post

@Composable
fun PostList(
    group: Group,
    posts: List<Post>,
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
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}