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
import ua.university.group.data.repository.PostRepository
import ua.university.group.ui.selected.item.GroupItem
import ua.university.group.ui.selected.item.PostItem
import ua.university.group.ui.selected.screen.SelectedScreenArgs
import ua.university.network.result.onResult
import ua.university.preferences.UserSharedPreferences
import ua.university.ui.mvi.BaseViewModel

class GroupViewModel @AssistedInject constructor(
    @Assisted private var args: SelectedScreenArgs,
    private val groupRepository: GroupRepository,
    private val postRepository: PostRepository,
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
        when (event) {
            is GroupUiEvent.LoadGroup -> updatePosts(currentState = uiState.value)
            is GroupUiEvent.BackClicked -> {
                _uiState.value = createInitialState()
                args.navs.onBackClicked()
            }
            is GroupUiEvent.UserAvatarClicked -> args.navs.onUserAvatarClicked()
            is GroupUiEvent.AddPostClicked -> args.navs.onAddPostClicked()
            is GroupUiEvent.EditPostClicked -> args.navs.onEditPostClicked(event.postId)
        }
    }

    private fun updatePosts(currentState: GroupUiState) {
        val innerGroupId = args.groupId
        viewModelScope.launch {
            groupRepository.updateUserGroup(args.groupId)
            val group = groupRepository.getUserGroup(args.groupId).first()
            val posts = postRepository.getGroupPosts(args.groupId).first().map { PostItem(
                id = it.id,
                creatorName = "${it.creatorFirstName} ${it.creatorLastName}",
                creatorColor = Color.Blue,
                creationDate = it.creationDate,
                title = it.title,
                content = it.description,
            ) }
            _uiState.value = GroupUiState.Loading(
                group = GroupItem(
                    name = group.title,
                    description = group.description,
                    coverColor = Color(0xFF3B79E8)
                ),
                posts = posts,
                isOwner = group.label == "admin"
            )
            postRepository.updateGroupPosts(groupId = innerGroupId).onResult(
                onSuccess = { /* state will be updated using flow */
                    val posts = postRepository.getGroupPosts(groupId = innerGroupId).first()
                    _uiState.value = GroupUiState.TasksFetched(
                        group = GroupItem(
                            name = group.title,
                            description = group.description,
                            coverColor = Color.Gray//group.color
                        ),
                        posts = posts.map {
                            PostItem(
                                id = it.id,
                                creatorName = "${group.creatorFirstName} ${group.creatorLastName}",
                                creatorColor = Color(0xFF3B79E8),
                                creationDate = it.creationDate,
                                content = it.description,
                                title = it.description
                            )
                        },
                        isOwner = group.label == "admin"
                    )
                },
                onFailure = {
                    _uiState.value = GroupUiState.ErrorLoadingTasks(
                        group = GroupItem(
                            name = group.title,
                            description = group.description,
                            coverColor = Color(0xFF3B79E8)
                        ),
                        posts = posts,
                        isOwner = group.label == "admin",
                        cause = it.cause.message,
                    )
                }
            )
        }
    }

}