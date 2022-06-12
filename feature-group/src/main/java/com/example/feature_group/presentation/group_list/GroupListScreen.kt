package com.example.feature_group.presentation.group_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.feature_group.presentation.common.composable.Drawer
import com.example.feature_group.presentation.group_list.composable.GroupList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupListScreen(
    viewModel: GroupListViewModel
) {
    val uiState by viewModel.uiState

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My groups")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        //LetterAvatar(letter = 'A', color = Color.Blue)
                        Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                    }
                }
            )
        },
        drawerContent = { Drawer() }
    ) {
        when (uiState) {
            is GroupListUiState.ErrorLoadingGroups -> TODO()
            is GroupListUiState.GroupsFetched -> {
                GroupList(
                    groups = (uiState as GroupListUiState.GroupsFetched).groups,
                    modifier = Modifier.padding(it)
                )
            }
            is GroupListUiState.LoadingGroups -> {
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

    LaunchedEffect(Unit) {
        viewModel.handleEvent(event = GroupListUiEvent.LoadGroups)
    }
}