package ua.university.group.ui.list.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ua.university.group.data.repository.GroupRepository
import ua.university.group.ui.list.item.toGroupItem
import ua.university.group.ui.list.screen.ListScreenArgs
import ua.university.network.result.onResult
import ua.university.ui.mvi.BaseViewModel

class GroupListViewModel @AssistedInject constructor(
    @Assisted private val args: ListScreenArgs,
    private val groupRepository: GroupRepository
) : BaseViewModel<GroupListUiEvent, GroupListUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: ListScreenArgs): GroupListViewModel
    }

    override fun createInitialState(): GroupListUiState {
        return GroupListUiState.LoadingGroups(emptyList())
    }

    init {
        viewModelScope.launch {
            _uiState.value = GroupListUiState.GroupsFetched(
                groupRepository.getUserGroups().first().map { it.toGroupItem() }
            )
        }
    }

    override fun handleEvent(event: GroupListUiEvent) {
        when (val currentState = _uiState.value) {
            is GroupListUiState.ErrorLoadingGroups -> reduce(event, currentState)
            is GroupListUiState.GroupsFetched ->  reduce(event, currentState)
            is GroupListUiState.LoadingGroups ->  reduce(event, currentState)
        }
    }

    private fun reduce(event: GroupListUiEvent, currentState: GroupListUiState.ErrorLoadingGroups) {
        when (event) {
            is GroupListUiEvent.AddGroupClicked -> args.navs.onAddGroupClicked()
            is GroupListUiEvent.JoinGroupClicked -> args.navs.onJoinGroupClicked()
            is GroupListUiEvent.LoadGroups -> updateGroups()
            is GroupListUiEvent.OpenGroup -> args.navs.onGroupClicked(event.groupId)
            is GroupListUiEvent.UserAvatarClicked -> args.navs.onUserAvatarClicked()
        }
    }

    private fun reduce(event: GroupListUiEvent, currentState: GroupListUiState.GroupsFetched) {
        when (event) {
            is GroupListUiEvent.AddGroupClicked -> args.navs.onAddGroupClicked()
            is GroupListUiEvent.JoinGroupClicked -> args.navs.onJoinGroupClicked()
            is GroupListUiEvent.LoadGroups -> updateGroups()
            is GroupListUiEvent.OpenGroup -> args.navs.onGroupClicked(event.groupId)
            is GroupListUiEvent.UserAvatarClicked -> args.navs.onUserAvatarClicked()
        }
    }

    private fun reduce(event: GroupListUiEvent, currentState: GroupListUiState.LoadingGroups) {
        when (event) {
            is GroupListUiEvent.AddGroupClicked -> args.navs.onAddGroupClicked()
            is GroupListUiEvent.JoinGroupClicked -> args.navs.onJoinGroupClicked()
            is GroupListUiEvent.LoadGroups -> updateGroups()
            is GroupListUiEvent.OpenGroup -> args.navs.onGroupClicked(event.groupId)
            is GroupListUiEvent.UserAvatarClicked -> args.navs.onUserAvatarClicked()
        }
    }

    private fun updateGroups() {
        viewModelScope.launch {
            _uiState.value = GroupListUiState.LoadingGroups(groups = uiState.value.groups)
            groupRepository.updateUserGroups().onResult(
                onSuccess = { /* state will be updated using flow */
                    _uiState.value = GroupListUiState.GroupsFetched(
                        groupRepository.getUserGroups().first().map { it.toGroupItem() }
                    )
                },
                onFailure = {
                    _uiState.value = GroupListUiState.ErrorLoadingGroups(
                        groups = uiState.value.groups,
                        cause = it.cause.message
                    )
                }
            )
        }
    }

}