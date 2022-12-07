package ua.university.group.ui.add.mvi

import ua.university.ui.mvi.UiEvent

sealed class AddGroupUiEvent : UiEvent {
    class UpdateGroupName(val name: String) : AddGroupUiEvent()
    class UpdateGroupDescription(val description: String) : AddGroupUiEvent()
    object CreateGroup : AddGroupUiEvent()
    object BackClicked : AddGroupUiEvent()
}