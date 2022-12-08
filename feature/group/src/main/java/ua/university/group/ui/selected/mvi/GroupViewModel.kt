package ua.university.group.ui.selected.mvi

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ua.university.group.data.entity.GroupEntity
import ua.university.group.data.entity.PostEntity
import ua.university.group.data.repository.GroupRepository
import ua.university.group.data.repository.PostRepository
import ua.university.group.ui.selected.item.GroupItem
import ua.university.group.ui.selected.item.toPostItem
import ua.university.group.ui.selected.screen.SelectedScreenArgs
import ua.university.network.result.onFailure
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
                coverColor = Color(0xFF3B79E8),
                code = "",
            ),
            posts = emptyList(),
            isOwner = false
        )
    }

    init {
        updateGroup()
    }

    override fun handleEvent(event: GroupUiEvent) {
        when (event) {
            is GroupUiEvent.LoadGroup -> updatePosts(currentState = uiState.value)
            is GroupUiEvent.BackClicked -> {
                _uiState.value = createInitialState()
                args.navs.onBackClicked()
            }
            is GroupUiEvent.UserAvatarClicked -> args.navs.onUserAvatarClicked()
            is GroupUiEvent.AddPostClicked -> args.navs.onAddPostClicked(args.groupId)
            is GroupUiEvent.EditPostClicked -> args.navs.onEditPostClicked(args.groupId, event.postId)
            is GroupUiEvent.DeleteGroupClicked -> {
                viewModelScope.launch {
                    groupRepository.deleteGroup(args.groupId)
                    args.navs.onDeleteGroupClicked()
                }
            }
            is GroupUiEvent.EditGroupClicked -> args.navs.onEditGroupClicked(args.groupId)
        }
    }

    private fun updatePosts(currentState: GroupUiState) {
        _uiState.value = GroupUiState.Loading(
            group = GroupItem(
                name = currentState.group.name,
                description = currentState.group.description,
                coverColor = currentState.group.coverColor,
                code = currentState.group.code,
            ),
            posts = currentState.posts,
            isOwner = currentState.isOwner
        )
        viewModelScope.launch {
            groupRepository.updateUserGroup(groupId = args.groupId)
                .onFailure { showError(it.cause.message); return@launch }
            postRepository.updateGroupPosts(groupId = args.groupId)
                .onFailure { showError(it.cause.message); return@launch }
        }
    }

    private fun updateGroup() {
        viewModelScope.launch {
            val groupFlow = combine(
                groupRepository.getUserGroup(args.groupId),
                postRepository.getGroupPosts(args.groupId)
            ) { groupEntity, postEntities ->
                Pair(groupEntity, postEntities)
            }

            groupFlow.collectLatest { pair: Pair<GroupEntity?, List<PostEntity>> ->
                val groupEntity = pair.first
                val group = GroupItem(
                    name = groupEntity?.title ?: "",
                    description = groupEntity?.description ?: "",
                    coverColor = Color(0xFF3B79E8),
                    code = groupEntity?.code ?: "",
                )
                val posts = pair.second.map { it.toPostItem() }

                _uiState.value = GroupUiState.TasksFetched(
                    group = group,
                    posts = posts,
                    isOwner = groupEntity?.label == "admin"
                )
            }
        }
    }

    private fun showError(cause: String?) {
        _uiState.value = GroupUiState.ErrorLoadingTasks(
            group = GroupItem(
                name = _uiState.value.group.name,
                description = _uiState.value.group.description,
                coverColor = _uiState.value.group.coverColor,
                code = _uiState.value.group.code,
            ),
            posts = _uiState.value.posts,
            isOwner = _uiState.value.isOwner,
            cause = cause
        )
    }
}