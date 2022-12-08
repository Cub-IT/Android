package ua.university.group.ui.edit.mvi

import ua.university.ui.mvi.UiEvent

sealed class EditGroupUiEvent : UiEvent {
    class UpdateGroupName(val name: String) : EditGroupUiEvent()
    class UpdateGroupDescription(val description: String) : EditGroupUiEvent()
    object CreateGroup : EditGroupUiEvent()
    object BackClicked : EditGroupUiEvent()
}