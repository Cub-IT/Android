package ua.university.group.list.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.university.group.R
import ua.university.group.list.item.previewGroupItemList
import ua.university.group.list.mvi.State
import ua.university.group.list.screen.composable.GroupList
import ua.university.ui.composable.IconAvatar
import ua.university.ui.item.Reloadable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(args: ListScreenArgs) {
    val uiState = State(Reloadable(
        value = previewGroupItemList(6),
        status = Reloadable.Status.Idle
    ))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My groups") },
                actions = {
                    IconButton(onClick = { args.navs.onUserAvatarClicked() }) {
                        IconAvatar(color = Color(0xFF3B79E8), size = 40.dp)
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = { args.navs.onJoinGroupClicked() }) {
                    Icon(painter = painterResource(R.drawable.baseline_group_add_24), contentDescription = null)
                }

                Spacer(modifier = Modifier.padding(8.dp))

                FloatingActionButton(onClick = { args.navs.onAddGroupClicked() }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxSize()) {
            if (uiState.groups.status is Reloadable.Status.Loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            /*if (uiState is GroupListUiState.ErrorLoadingGroups) {
                ErrorMessage(
                    errorCause = (uiState as GroupListUiState.ErrorLoadingGroups).cause,
                    modifier = Modifier.padding(16.dp)
                )
            }*/

            GroupList(
                groups = uiState.groups.value,
                onGroupClick = { groupId -> args.navs.onGroupClicked(groupId) }
            )
        }
    }

    /*LaunchedEffect(Unit) {
        viewModel.handleEvent(event = GroupListUiEvent.LoadGroups)
    }*/
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(ListScreenArgs(ListScreenNavs({}, {}, {}, {})))
}