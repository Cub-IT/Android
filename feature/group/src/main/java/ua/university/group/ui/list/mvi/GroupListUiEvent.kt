package ua.university.group.ui.list.mvi

import ua.university.ui.mvi.UiEvent

sealed class GroupListUiEvent : UiEvent {

    object LoadGroups : GroupListUiEvent()

    object UserAvatarClicked : GroupListUiEvent()

    object AddGroupClicked : GroupListUiEvent()

    object JoinGroupClicked : GroupListUiEvent()

    data class OpenGroup(val groupId: String) : GroupListUiEvent()

}