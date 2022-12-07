package ua.university.group.ui.add.mvi

import ua.university.group.ui.add.item.NewGroupItem
import ua.university.ui.mvi.UiState

sealed class AddGroupUiState(
    val group: NewGroupItem,
    val isCreationEnabled: Boolean
) : UiState {

    class WaitingGroupData(group: NewGroupItem, isCreationEnabled: Boolean)
        : AddGroupUiState(group = group, isCreationEnabled = isCreationEnabled)

    class WaitingResponse(group: NewGroupItem)
        : AddGroupUiState(group = group, isCreationEnabled = false)

    class FailedCreation(group: NewGroupItem, isCreationEnabled: Boolean, val cause: String?)
        : AddGroupUiState(group = group, isCreationEnabled = isCreationEnabled)
}
