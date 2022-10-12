package ua.university.group.selected.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.university.group.selected.item.previewGroupItem
import ua.university.group.selected.item.previewPostItemList
import ua.university.group.selected.mvi.State
import ua.university.group.selected.screen.composable.PostList
import ua.university.ui.composable.IconAvatar
import ua.university.ui.item.Reloadable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedScreen(args: SelectedScreenArgs) {
    val uiState = State(
        group = Reloadable(previewGroupItem(), Reloadable.Status.Idle),
        posts = Reloadable(previewPostItemList(), Reloadable.Status.Idle),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Name of the group")
                },
                navigationIcon = {
                    IconButton(onClick = { args.navs.onBackClicked() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { args.navs.onUserAvatarClicked() }) {
                        IconAvatar(color = Color(0xFF3B79E8), size = 40.dp)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxSize()) {
            if (uiState.group.status is Reloadable.Status.Idle ||
                uiState.posts.status is Reloadable.Status.Idle) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            /*if (uiState.group.status is Reloadable.Status.Error ||
                uiState.posts.status is Reloadable.Status.Error) {
                ErrorMessage(
                    errorCause = (uiState as GroupUiState.ErrorLoadingTasks).cause,
                    modifier = Modifier.padding(16.dp)
                )
            }*/

            PostList(
                group = uiState.group.value,
                posts = uiState.posts.value,
                onPostClick = { postId -> args.navs.onPostClicked(postId) }
            )
        }
    }
}