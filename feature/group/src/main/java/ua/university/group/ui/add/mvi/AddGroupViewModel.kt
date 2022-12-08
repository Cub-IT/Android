package ua.university.group.ui.add.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.group.data.repository.GroupRepository
import ua.university.group.ui.add.item.NewGroupItem
import ua.university.group.ui.add.screen.AddGroupScreenArgs
import ua.university.network.result.onResult
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class AddGroupViewModel @AssistedInject constructor(
    @Assisted private val args: AddGroupScreenArgs,
    private val groupRepository: GroupRepository,
    @Named("longNameLinter") private val longNameLinter: InputLinter
) : BaseViewModel<AddGroupUiEvent, AddGroupUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: AddGroupScreenArgs): AddGroupViewModel
    }

    override fun createInitialState(): AddGroupUiState {
        val group = NewGroupItem(
            name = InputFiled(""),
            description = InputFiled("")
        )
        return AddGroupUiState.WaitingGroupData(
            group = group,
            isCreationEnabled = isCreationEnabled(group)
        )
    }

    override fun handleEvent(event: AddGroupUiEvent) {
        when (val currentState = uiState.value) {
            is AddGroupUiState.FailedCreation -> reduce(event, currentState)
            is AddGroupUiState.WaitingGroupData -> reduce(event, currentState)
            is AddGroupUiState.WaitingResponse -> throw IllegalStateException()
        }
    }

    private fun reduce(event: AddGroupUiEvent, currentState: AddGroupUiState.FailedCreation) {
        when (event) {
            is AddGroupUiEvent.BackClicked -> args.navs.onBackClicked()
            is AddGroupUiEvent.CreateGroup -> createNewGroup(currentState.group)
            is AddGroupUiEvent.UpdateGroupDescription -> {
                val groupInput = NewGroupItem(
                    name = currentState.group.name,
                    description = InputFiled(event.description)
                )
                _uiState.value = AddGroupUiState.FailedCreation(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput),
                    cause = currentState.cause
                )
            }
            is AddGroupUiEvent.UpdateGroupName -> {
                val groupInput = NewGroupItem(
                    name = getNewName(event.name),
                    description = currentState.group.description
                )
                _uiState.value = AddGroupUiState.FailedCreation(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput),
                    cause = currentState.cause
                )
            }
        }
    }

    private fun reduce(event: AddGroupUiEvent, currentState: AddGroupUiState.WaitingGroupData) {
        when (event) {
            is AddGroupUiEvent.BackClicked -> args.navs.onBackClicked()
            is AddGroupUiEvent.CreateGroup -> createNewGroup(currentState.group)
            is AddGroupUiEvent.UpdateGroupDescription -> {
                val groupInput = NewGroupItem(
                    name = currentState.group.name,
                    description = InputFiled(event.description)
                )
                _uiState.value = AddGroupUiState.WaitingGroupData(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput)
                )
            }
            is AddGroupUiEvent.UpdateGroupName -> {
                val groupInput = NewGroupItem(
                    name = getNewName(event.name),
                    description = currentState.group.description
                )
                _uiState.value = AddGroupUiState.WaitingGroupData(
                    group = groupInput,
                    isCreationEnabled = isCreationEnabled(groupInput)
                )
            }
        }
    }

    private fun isCreationEnabled(group: NewGroupItem): Boolean {
        return (group.name.error == null) and
                group.name.value.isNotEmpty()
    }

    private fun createNewGroup(group: NewGroupItem) {
        viewModelScope.launch {
            groupRepository.createGroup(
                name = group.name.value,
                description = group.description.value
            ).onResult(
                onSuccess = { args.navs.onCreateGroupClicked(it.success.id) },
                onFailure = {
                    _uiState.value = AddGroupUiState.FailedCreation(
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