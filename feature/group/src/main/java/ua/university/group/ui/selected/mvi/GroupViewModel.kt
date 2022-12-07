package ua.university.group.ui.selected.mvi

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ua.university.group.data.repository.GroupRepository
import ua.university.group.ui.selected.item.GroupItem
import ua.university.group.ui.selected.item.PostItem
import ua.university.group.ui.selected.screen.SelectedScreenArgs
import ua.university.network.result.onResult
import ua.university.preferences.UserSharedPreferences
import ua.university.ui.mvi.BaseViewModel

class GroupViewModel @AssistedInject constructor(
    @Assisted private var args: SelectedScreenArgs,
    private val groupRepository: GroupRepository,
    private val userSource: UserSharedPreferences
) : BaseViewModel<GroupUiEvent, GroupUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: SelectedScreenArgs): GroupViewModel
    }

    override fun createInitialState(): GroupUiState {
        return GroupUiState.Loading(
            group = GroupItem(
                name = "",
                description = "",
                coverColor = Color(0xFF3B79E8)
            ),
            posts = emptyList(),
            isOwner = false
        )
    }

    override fun handleEvent(event: GroupUiEvent) {
        /*when (event) {
            is GroupUiEvent.LoadGroup -> updatePosts(currentState = uiState.value)
            is GroupUiEvent.BackClicked -> {
                _uiState.value = createInitialState()
                args.navs.onBackClicked()
            }
            is GroupUiEvent.UserAvatarClicked -> args.navs.onUserAvatarClicked()
            is GroupUiEvent.AddPostClicked -> args.navs.onAddPostClicked()
            is GroupUiEvent.EditPostClicked -> args.navs.onEditPostClicked(event.postId)
        }*/
    }

    /*private fun updatePosts(currentState: GroupUiState) {
        val innerGroupId = args.groupId
        viewModelScope.launch {
            val group = groupRepository.getUserGroup(args.groupId).first()
            _uiState.value = GroupUiState.Loading(
                group = currentState.group,
                posts = currentState.posts,
                isOwner = false//userSource.getUser()!!.id == group.ownerId
            )
            groupRepository.updateGroupPosts(groupId = innerGroupId).onResult(
                onSuccess = { /* state will be updated using flow */
                    val posts = groupRepository.getGroupPosts(groupId = innerGroupId).first().reversed()
                    _uiState.value = GroupUiState.TasksFetched(
                        group = GroupItem(
                            name = group.title,
                            description = group.description,
                            coverColor = Color.Gray//group.color
                        ),
                        posts = posts.map {
                            PostItem(
                                id = it.id,
                                creatorName = group.ownerLastName,
                                creatorColor = Color(0xFF3B79E8),
                                creationDate = it.creationDate,
                                content = it.description,
                                title = it.description
                            )
                        },
                        isOwner = false//userSource.getUser()!!.id == group.ownerId
                    )
                },
                onFailure = {
                    _uiState.value = GroupUiState.ErrorLoadingTasks(
                        group = currentState.group,
                        posts = currentState.posts,
                        isOwner = currentState.isOwner,
                        cause = it.cause.message,
                    )
                }
            )
        }
    }*/

}