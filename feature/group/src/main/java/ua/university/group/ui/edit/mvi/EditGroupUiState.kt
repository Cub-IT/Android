package ua.university.group.ui.edit.mvi

import ua.university.group.ui.edit.item.EditGroupItem
import ua.university.ui.mvi.UiState

sealed class EditGroupUiState(
    val group: EditGroupItem,
    val isCreationEnabled: Boolean
) : UiState {

    class WaitingGroupData(group: EditGroupItem, isCreationEnabled: Boolean)
        : EditGroupUiState(group = group, isCreationEnabled = isCreationEnabled)

    class WaitingResponse(group: EditGroupItem)
        : EditGroupUiState(group = group, isCreationEnabled = false)

    class FailedCreation(group: EditGroupItem, isCreationEnabled: Boolean, val cause: String?)
        : EditGroupUiState(group = group, isCreationEnabled = isCreationEnabled)
}
