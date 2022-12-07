package ua.university.group.ui.add.screen

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
import ua.university.group.ui.add.mvi.AddGroupUiEvent
import ua.university.group.ui.add.mvi.AddGroupUiState
import ua.university.group.ui.add.mvi.AddGroupViewModel
import ua.university.group.ui.add.screen.composable.Fields
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.toUiText


@Composable
fun AddGroupScreen(
    args: AddGroupScreenArgs,
    factory: AddGroupViewModel.Factory,
    viewModel: AddGroupViewModel = ua.university.ui.util.viewModel { factory.create(args) }
) {
    AddGroupScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGroupScreen(
    uiState: AddGroupUiState,
    handleEvent: (AddGroupUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create group")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        handleEvent(AddGroupUiEvent.BackClicked)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { handleEvent(AddGroupUiEvent.CreateGroup) },
                        enabled = uiState.isCreationEnabled
                    ) {
                        Text(text = "Create")
                    }
                }
            )
        }
    ) {
        when (uiState) {
            is AddGroupUiState.FailedCreation,
            is AddGroupUiState.WaitingGroupData -> {
                Box(Modifier.fillMaxSize()) {
                    if (uiState is AddGroupUiState.FailedCreation) {
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
            is AddGroupUiState.WaitingResponse -> {
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