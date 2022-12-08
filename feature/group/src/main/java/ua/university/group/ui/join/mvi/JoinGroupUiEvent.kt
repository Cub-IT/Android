package ua.university.group.ui.join.mvi

import ua.university.ui.mvi.UiEvent

sealed class JoinGroupUiEvent : UiEvent {
    class UpdateGroupCode(val code: String) : JoinGroupUiEvent()
    object JoinGroup : JoinGroupUiEvent()
    object BackClicked : JoinGroupUiEvent()
}