package ua.university.group.ui.edit.screen

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
import ua.university.group.ui.edit.mvi.EditGroupUiEvent
import ua.university.group.ui.edit.mvi.EditGroupUiState
import ua.university.group.ui.edit.mvi.EditGroupViewModel
import ua.university.group.ui.edit.screen.composable.Fields
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.toUiText

@Composable
fun EditGroupScreen(
    args: EditGroupScreenArgs,
    factory: EditGroupViewModel.Factory,
    viewModel: EditGroupViewModel = ua.university.ui.util.viewModel { factory.create(args) }
) {
    EditGroupScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGroupScreen(
    uiState: EditGroupUiState,
    handleEvent: (EditGroupUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit group")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        handleEvent(EditGroupUiEvent.BackClicked)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { handleEvent(EditGroupUiEvent.CreateGroup) },
                        enabled = uiState.isCreationEnabled
                    ) {
                        Text(text = "Edit")
                    }
                }
            )
        }
    ) {
        when (uiState) {
            is EditGroupUiState.FailedCreation,
            is EditGroupUiState.WaitingGroupData -> {
                Box(Modifier.fillMaxSize()) {
                    if (uiState is EditGroupUiState.FailedCreation) {
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
            is EditGroupUiState.WaitingResponse -> {
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