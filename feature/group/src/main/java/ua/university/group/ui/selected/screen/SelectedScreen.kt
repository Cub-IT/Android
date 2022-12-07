package ua.university.group.ui.selected.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import ua.university.group.ui.selected.mvi.GroupUiEvent
import ua.university.group.ui.selected.mvi.GroupUiState
import ua.university.group.ui.selected.mvi.GroupViewModel
import ua.university.group.ui.selected.screen.composable.PostList
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.IconAvatar
import ua.university.ui.item.toUiText

@Composable
fun SelectedScreen(
    args: SelectedScreenArgs,
    factory: GroupViewModel.Factory,
    viewModel: GroupViewModel = ua.university.ui.util.viewModel { factory.create(args) }
) {
    SelectedScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedScreen(
    uiState: GroupUiState,
    handleEvent: (GroupUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = uiState.group.name)
                },
                navigationIcon = {
                    IconButton(onClick = { handleEvent(GroupUiEvent.BackClicked) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        handleEvent(GroupUiEvent.UserAvatarClicked)
                    }) {
                        IconAvatar(color = Color(0xFF3B79E8), size = 40.dp)
                    }
                    IconButton(onClick = {
                        handleEvent(GroupUiEvent.LoadGroup)
                    }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            if (uiState.isOwner) {
                FloatingActionButton(onClick = {
                    handleEvent(GroupUiEvent.AddPostClicked)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            if (uiState is GroupUiState.Loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (uiState is GroupUiState.ErrorLoadingTasks) {
                CubitErrorMessage(
                    errorCause = uiState.cause.toUiText(),
                    modifier = Modifier.padding(16.dp)
                )
            }

            PostList(
                group = uiState.group,
                posts = uiState.posts,
                onPostClick = { postId -> handleEvent(GroupUiEvent.EditPostClicked(postId = postId)) }
            )
        }
    }
}