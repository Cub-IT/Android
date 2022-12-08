package ua.university.post.ui.edit.screen

import androidx.compose.foundation.layout.Box
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
import ua.university.post.ui.edit.mvi.EditPostUiEvent
import ua.university.post.ui.edit.mvi.EditPostUiState
import ua.university.post.ui.edit.mvi.EditPostViewModel
import ua.university.post.ui.edit.screen.composable.Fields
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.toUiText

@Composable
fun EditPostScreen(
    args: EditPostScreenArgs,
    factory: EditPostViewModel.Factory,
    viewModel: EditPostViewModel = ua.university.ui.util.viewModel { factory.create(args) }
) {
    EditPostScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPostScreen(
    uiState: EditPostUiState,
    handleEvent: (EditPostUiEvent) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit post")
                },
                navigationIcon = {
                    IconButton(onClick = { handleEvent(EditPostUiEvent.BackClicked) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { handleEvent(EditPostUiEvent.EditPost) },
                        enabled = uiState.isPostingEnabled
                    ) {
                        Text(text = "Edit")
                    }
                }
            )
        }
    ) {
        when (uiState) {
            is EditPostUiState.FailedAddingPost,
            is EditPostUiState.WaitingPostData -> {
                Box(Modifier.fillMaxSize().padding(it)) {
                    if (uiState is EditPostUiState.FailedAddingPost) {
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
            is EditPostUiState.WaitingResponse -> {
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