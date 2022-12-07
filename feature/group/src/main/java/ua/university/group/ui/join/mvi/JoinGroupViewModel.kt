package ua.university.group.ui.join.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.group.data.repository.GroupRepository
import ua.university.group.ui.join.screen.JoinGroupScreenArgs
import ua.university.network.result.onResult
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class JoinGroupViewModel @AssistedInject constructor(
    @Assisted private val args: JoinGroupScreenArgs,
    private val groupRepository: GroupRepository,
    @Named("groupCodeLinter") private val groupCodeLinter: InputLinter
) : BaseViewModel<JoinGroupUiEvent, JoinGroupUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: JoinGroupScreenArgs): JoinGroupViewModel
    }

    override fun createInitialState(): JoinGroupUiState {
        val groupCode = InputFiled("")
        return JoinGroupUiState.WaitingGroupData(
            groupCode = groupCode,
            isJoiningEnabled = isJoiningEnabled(groupCode)
        )
    }

    override fun handleEvent(event: JoinGroupUiEvent) {
        when (val currentState = uiState.value) {
            is JoinGroupUiState.FailedCreation -> reduce(event, currentState)
            is JoinGroupUiState.WaitingGroupData -> reduce(event, currentState)
            is JoinGroupUiState.WaitingResponse -> throw IllegalStateException()
        }
    }

    private fun reduce(event: JoinGroupUiEvent, currentState: JoinGroupUiState.FailedCreation) {
        when (event) {
            is JoinGroupUiEvent.BackClicked -> args.navs.onBackClicked()
            is JoinGroupUiEvent.JoinGroup -> createNewGroup(currentState.groupCode)
            is JoinGroupUiEvent.UpdateGroupCode -> {
                val groupCode = getNewCode(event.code)
                _uiState.value = JoinGroupUiState.FailedCreation(
                    groupCode = groupCode,
                    isJoiningEnabled = isJoiningEnabled(groupCode),
                    cause = currentState.cause
                )
            }
        }
    }

    private fun reduce(event: JoinGroupUiEvent, currentState: JoinGroupUiState.WaitingGroupData) {
        when (event) {
            is JoinGroupUiEvent.BackClicked -> args.navs.onBackClicked()
            is JoinGroupUiEvent.JoinGroup -> createNewGroup(currentState.groupCode)
            is JoinGroupUiEvent.UpdateGroupCode -> {
                val groupCode = getNewCode(event.code)
                _uiState.value = JoinGroupUiState.WaitingGroupData(
                    groupCode = groupCode,
                    isJoiningEnabled = isJoiningEnabled(groupCode)
                )
            }
        }
    }

    private fun isJoiningEnabled(groupCode: InputFiled): Boolean {
        return (groupCode.error == null) and
                groupCode.value.isNotEmpty()
    }

    private fun createNewGroup(groupCode: InputFiled) {
        viewModelScope.launch {
            groupRepository.joinToGroup(groupId = groupCode.value).onResult(
                onSuccess = { args.navs.onJoinGroupClicked(it.success) },
                onFailure = {
                    _uiState.value = JoinGroupUiState.FailedCreation(
                        groupCode = groupCode,
                        isJoiningEnabled = isJoiningEnabled(groupCode),
                        cause = it.cause.message
                    )
                }
            )
        }
    }

    private fun getNewCode(code: String): InputFiled {
        return InputFiled(
            value = code,
            error = groupCodeLinter.check(code.trim())
        )
    }

}