package ua.university.group.ui.selected.mvi

import ua.university.ui.mvi.UiEvent

sealed class GroupUiEvent : UiEvent {
    object LoadGroup : GroupUiEvent()
    object UserAvatarClicked : GroupUiEvent()
    object BackClicked : GroupUiEvent()
    object AddPostClicked : GroupUiEvent()
    data class EditPostClicked(val postId: String) : GroupUiEvent()
    object EditGroupClicked : GroupUiEvent()
    object DeleteGroupClicked : GroupUiEvent()

}