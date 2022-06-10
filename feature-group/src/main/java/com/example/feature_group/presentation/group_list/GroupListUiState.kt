package com.example.feature_group.presentation.group_list

import com.example.core.presentation.UiState
import com.example.feature_group.presentation.group_list.item.GroupListItem

sealed class GroupListUiState : UiState() {

    object LoadingGroups : GroupListUiState()

    class GroupsFetched(val groups: List<GroupListItem>) : GroupListUiState()

    class ErrorLoadingGroups(val groups: List<GroupListItem>, reason: String) : GroupListUiState()

}