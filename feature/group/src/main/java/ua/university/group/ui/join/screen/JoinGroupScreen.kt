package ua.university.group.ui.join.screen

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
import ua.university.group.R
import ua.university.group.ui.join.mvi.JoinGroupUiEvent
import ua.university.group.ui.join.mvi.JoinGroupUiState
import ua.university.group.ui.join.mvi.JoinGroupViewModel
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.CubitTextField
import ua.university.ui.item.UiText
import ua.university.ui.item.toUiText
import ua.university.ui.util.viewModel

@Composable
fun JoinGroupScreen(
    args: JoinGroupScreenArgs,
    factory: JoinGroupViewModel.Factory,
    viewModel: JoinGroupViewModel = viewModel { factory.create(args) }
) {
    JoinGroupScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinGroupScreen(
    uiState: JoinGroupUiState,
    handleEvent: (JoinGroupUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Join group")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        handleEvent(JoinGroupUiEvent.BackClicked)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { handleEvent(JoinGroupUiEvent.JoinGroup) },
                        enabled = uiState.isJoiningEnabled
                    ) {
                        Text(text = "Create")
                    }
                }
            )
        }
    ) {
        when (uiState) {
            is JoinGroupUiState.FailedCreation,
            is JoinGroupUiState.WaitingGroupData -> {
                Box(Modifier.fillMaxSize()) {
                    if (uiState is JoinGroupUiState.FailedCreation) {
                        CubitErrorMessage(
                            errorCause = uiState.cause.toUiText(),
                            modifier = Modifier
                                .padding(16.dp)
                                .padding(it)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Asc your teacher for the group code, then enter it here.")
                        CubitTextField(
                            field = uiState.groupCode,
                            label = UiText.StringResource(R.string.group_code),
                            onValueChange = { newValue ->
                                handleEvent(JoinGroupUiEvent.UpdateGroupCode(newValue))
                            }
                        )
                    }
                }
            }
            is JoinGroupUiState.WaitingResponse -> {
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