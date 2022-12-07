package ua.university.group.ui.selected.mvi

import ua.university.group.ui.selected.item.GroupItem
import ua.university.group.ui.selected.item.PostItem
import ua.university.ui.mvi.UiState

sealed class GroupUiState(
    val group: GroupItem,
    val posts: List<PostItem>,
    val isOwner: Boolean
) : UiState {

    class Loading(group: GroupItem, posts: List<PostItem>, isOwner: Boolean)
        : GroupUiState(group = group, posts = posts, isOwner = isOwner)

    class TasksFetched(group: GroupItem, posts: List<PostItem>, isOwner: Boolean)
        : GroupUiState(group = group, posts = posts, isOwner = isOwner)

    class ErrorLoadingTasks(group: GroupItem, posts: List<PostItem>, isOwner: Boolean, val cause: String?)
        : GroupUiState(group = group, posts = posts, isOwner = isOwner)

}