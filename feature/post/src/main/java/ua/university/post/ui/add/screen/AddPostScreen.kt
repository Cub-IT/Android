package ua.university.post.ui.add.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.post.R
import ua.university.post.ui.add.item.previewNewPostItem
import ua.university.post.ui.add.mvi.AddPostUiEvent
import ua.university.post.ui.add.mvi.AddPostUiState
import ua.university.post.ui.add.mvi.AddPostViewModel
import ua.university.post.ui.add.screen.composable.Fields
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.CubitTextField
import ua.university.ui.composable.FullscreenProgressIndicator
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText
import ua.university.ui.item.toUiText

@Composable
fun AddPostScreen(
    args: AddPostScreenArgs,
    factory: AddPostViewModel.Factory,
    viewModel: AddPostViewModel = ua.university.ui.util.viewModel { factory.create(args) }
) {
    AddPostScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(
    uiState: AddPostUiState,
    handleEvent: (AddPostUiEvent) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add post")
                },
                navigationIcon = {
                    IconButton(onClick = { handleEvent(AddPostUiEvent.BackClicked) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { handleEvent(AddPostUiEvent.AddPost) },
                        enabled = uiState.isPostingEnabled
                    ) {
                        Text(text = "Create")
                    }
                }
            )
        }
    ) {
        when (uiState) {
            is AddPostUiState.FailedAddingPost,
            is AddPostUiState.WaitingPostData -> {
                Box(Modifier.fillMaxSize().padding(it)) {
                    if (uiState is AddPostUiState.FailedAddingPost) {
                        CubitErrorMessage(
                            errorCause = uiState.cause.toUiText(),
                            modifier = Modifier
                                .padding(16.dp)
                                .padding(it)
                        )
                    }

                    Fields(
                        uiState = uiState,
                        handleEvent = handleEvent,
                    )
                }
            }
            is AddPostUiState.WaitingResponse -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}