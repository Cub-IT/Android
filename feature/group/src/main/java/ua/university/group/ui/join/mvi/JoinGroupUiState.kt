package ua.university.group.ui.join.mvi

import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.UiState

sealed class JoinGroupUiState(
    val groupCode: InputFiled,
    val isJoiningEnabled: Boolean
) : UiState {

    class WaitingGroupData(groupCode: InputFiled, isJoiningEnabled: Boolean)
        : JoinGroupUiState(groupCode = groupCode, isJoiningEnabled = isJoiningEnabled)

    class WaitingResponse(groupCode: InputFiled)
        : JoinGroupUiState(groupCode = groupCode, isJoiningEnabled = false)

    class FailedCreation(groupCode: InputFiled, isJoiningEnabled: Boolean, val cause: String?)
        : JoinGroupUiState(groupCode = groupCode, isJoiningEnabled = isJoiningEnabled)
}