package ua.university.group.ui.list.mvi

import ua.university.group.ui.list.item.GroupItem
import ua.university.ui.mvi.UiState

sealed class GroupListUiState(val groups: List<GroupItem>) : UiState {

    class LoadingGroups(groups: List<GroupItem>) : GroupListUiState(groups = groups)

    class GroupsFetched(groups: List<GroupItem>) : GroupListUiState(groups = groups)

    class ErrorLoadingGroups(groups: List<GroupItem>, val cause: String?) : GroupListUiState(groups = groups)

}