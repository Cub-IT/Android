package ua.university.group.ui.edit.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ua.university.group.data.repository.GroupRepository
import ua.university.group.ui.edit.item.EditGroupItem
import ua.university.group.ui.edit.screen.EditGroupScreenArgs
import ua.university.network.result.onResult
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class EditGroupViewModel @AssistedInject constructor(
    @Assisted private val args: EditGroupScreenArgs,
    private val groupRepository: GroupRepository,
    @Named("longNameLinter") private val longNameLinter: InputLinter
) : BaseViewModel<EditGroupUiEvent, EditGroupUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: EditGroupScreenArgs): EditGroupViewModel
    }

    override fun createInitialState(): EditGroupUiState {
        viewModelScope.launch {
            delay(10)
            val group = groupRepository.getUserGroup(args.groupId).first()
            _uiState.value = EditGroupUiState.WaitingGroupData(
                group = EditGroupItem(
                    name = InputFiled(group.title),
                    description = InputFiled(group.description)
                ),
                isCreationEnabled = false
            )
        }

        val group = EditGroupItem(
            name = InputFiled(""),
            description = InputFiled("")
        )
        return EditGroupUiState.WaitingGroupData(
            group = group,
            isCreationEnabled = isCreationEnabled(group)
        )
    }

    override fun handleEvent(event: EditGroupUiEvent) {
        when (val currentState = uiState.value) {
            is EditGroupUiState.FailedCreation -> reduce(event, currentState)
            is EditGroupUiState.WaitingGroupData -> reduce(event, currentState)
            is EditGroupUiState.WaitingResponse -> throw IllegalStateException()
        }
    }

    private fun reduce(event: EditGroupUiEvent, currentState: EditGroupUiState.FailedCreation) {
        when (event) {
            is EditGroupUiEvent.BackClicked -> args.navs.onBackClicked()
            is EditGroupUiEvent.CreateGroup -> editGroup(currentState.group)
            is EditGroupUiEvent.UpdateGroupDescription -> {
                val groupInput = EditGroupItem(
                    name = currentState.group.name,
                    description = InputFiled(event.description)
                )
                _uiState.value = EditGroupUiState.FailedCreation(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput),
                    cause = currentState.cause
                )
            }
            is EditGroupUiEvent.UpdateGroupName -> {
                val groupInput = EditGroupItem(
                    name = getNewName(event.name),
                    description = currentState.group.description
                )
                _uiState.value = EditGroupUiState.FailedCreation(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput),
                    cause = currentState.cause
                )
            }
        }
    }

    private fun reduce(event: EditGroupUiEvent, currentState: EditGroupUiState.WaitingGroupData) {
        when (event) {
            is EditGroupUiEvent.BackClicked -> args.navs.onBackClicked()
            is EditGroupUiEvent.CreateGroup -> editGroup(currentState.group)
            is EditGroupUiEvent.UpdateGroupDescription -> {
                val groupInput = EditGroupItem(
                    name = currentState.group.name,
                    description = InputFiled(event.description)
                )
                _uiState.value = EditGroupUiState.WaitingGroupData(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput)
                )
            }
            is EditGroupUiEvent.UpdateGroupName -> {
                val groupInput = EditGroupItem(
                    name = getNewName(event.name),
                    description = currentState.group.description
                )
                _uiState.value = EditGroupUiState.WaitingGroupData(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput)
                )
            }
        }
    }

    private fun isCreationEnabled(group: EditGroupItem): Boolean {
        return (group.name.error == null) and
                group.name.value.isNotEmpty()
    }

    private fun editGroup(group: EditGroupItem) {
        viewModelScope.launch {
            groupRepository.editGroup(
                name = group.name.value,
                description = group.description.value,
                groupId = args.groupId,
            ).onResult(
                onSuccess = { args.navs.onCreateGroupClicked(it.success.id) },
                onFailure = {
                    _uiState.value = EditGroupUiState.FailedCreation(
                        group = group,
                        isCreationEnabled = isCreationEnabled(group),
                        cause = it.cause.message
                    )
                }
            )
        }
    }

    private fun getNewName(name: String): InputFiled {
        return InputFiled(
            value = name,
            error = longNameLinter.check(name.trim())
        )
    }

}