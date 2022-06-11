package com.example.feature_group.presentation.group_list

import com.example.core.presentation.UiState
import com.example.feature_group.presentation.common.item.GroupItem

sealed class GroupListUiState : UiState() {

    object LoadingGroups : GroupListUiState()

    class GroupsFetched(val groups: List<GroupItem>) : GroupListUiState()

    class ErrorLoadingGroups(val groups: List<GroupItem>, reason: String) : GroupListUiState()

}