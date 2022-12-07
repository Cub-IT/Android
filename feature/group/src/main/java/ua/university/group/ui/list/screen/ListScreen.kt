package ua.university.group.ui.list.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.university.group.R
import ua.university.group.ui.list.mvi.GroupListUiEvent
import ua.university.group.ui.list.mvi.GroupListUiState
import ua.university.group.ui.list.mvi.GroupListViewModel
import ua.university.group.ui.list.screen.composable.GroupList
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.IconAvatar
import ua.university.ui.item.Reloadable
import ua.university.ui.item.toUiText

@Composable
fun ListScreen(
    args: ListScreenArgs,
    factory: GroupListViewModel.Factory,
    viewModel: GroupListViewModel = ua.university.ui.util.viewModel { factory.create(args) }
) {
    ListScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    uiState: GroupListUiState,
    handleEvent: (GroupListUiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My groups") },
                actions = {
                    IconButton(onClick = {
                        handleEvent(GroupListUiEvent.UserAvatarClicked)
                    }) {
                        IconAvatar(color = Color(0xFF3B79E8), size = 40.dp)
                    }
                    IconButton(onClick = {
                        handleEvent(GroupListUiEvent.LoadGroups)
                    }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = {
                    handleEvent(GroupListUiEvent.JoinGroupClicked)
                }) {
                    Icon(painter = painterResource(R.drawable.baseline_group_add_24), contentDescription = null)
                }

                Spacer(modifier = Modifier.padding(8.dp))

                FloatingActionButton(onClick = {
                    handleEvent(GroupListUiEvent.AddGroupClicked)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            if (uiState is GroupListUiState.LoadingGroups) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (uiState is GroupListUiState.ErrorLoadingGroups) {
                CubitErrorMessage(
                    errorCause = uiState.cause.toUiText(),
                    modifier = Modifier.padding(16.dp)
                )
            }

            GroupList(
                groups = uiState.groups,
                onGroupClick = { groupId ->
                    handleEvent(GroupListUiEvent.OpenGroup(groupId))
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        handleEvent(GroupListUiEvent.LoadGroups)
    }
}
