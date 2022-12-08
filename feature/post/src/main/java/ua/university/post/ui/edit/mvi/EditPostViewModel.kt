package ua.university.post.ui.edit.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.university.network.result.onFailure
import ua.university.network.result.onResult
import ua.university.post.data.repository.PostRepository
import ua.university.post.ui.edit.screen.EditPostScreenArgs
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class EditPostViewModel @AssistedInject constructor(
    @Assisted private val args: EditPostScreenArgs,
    private val postRepository: PostRepository,
    @Named("nameLinter") private val nameLinter: InputLinter,
    @Named("postContentLinter") private val postContentLinter: InputLinter,
) : BaseViewModel<EditPostUiEvent, EditPostUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: EditPostScreenArgs): EditPostViewModel
    }

    override fun createInitialState(): EditPostUiState {
        viewModelScope.launch {
            delay(10)
            val post = postRepository.getPost(
                groupCode = args.groupId,
                postId = args.postId,
            ).onFailure {
                _uiState.value = EditPostUiState.FailedAddingPost(
                    postTitle = _uiState.value.postTitle,
                    postContent = _uiState.value.postContent,
                    isPostingEnabled = false,
                    cause = it.cause.message
                )
                return@launch
            }
            _uiState.value = EditPostUiState.WaitingPostData(
                postTitle = InputFiled(post.title),
                postContent = InputFiled(post.description),
                isPostingEnabled = false,
            )
        }

        val postTitle = InputFiled("")
        val postContent = InputFiled("")
        return EditPostUiState.WaitingPostData(
            postTitle = postTitle,
            postContent = postContent,
            isPostingEnabled = isPostingEnabled(postTitle, postContent)
        )
    }

    override fun handleEvent(event: EditPostUiEvent) {
        when (val currentState = uiState.value) {
            is EditPostUiState.FailedAddingPost -> reduce(event, currentState)
            is EditPostUiState.WaitingPostData -> reduce(event, currentState)
            is EditPostUiState.WaitingResponse -> throw IllegalStateException()
        }
    }

    private fun reduce(event: EditPostUiEvent, currentState: EditPostUiState.FailedAddingPost) {
        when (event) {
            is EditPostUiEvent.BackClicked -> args.navs.onBackClicked()
            is EditPostUiEvent.EditPost -> editPost(currentState.postTitle, currentState.postContent)
            is EditPostUiEvent.UpdatePostContent -> {
                val postContent = getNewContent(event.content)
                _uiState.value = EditPostUiState.FailedAddingPost(
                    postTitle = currentState.postTitle,
                    postContent = postContent,
                    isPostingEnabled = isPostingEnabled(currentState.postTitle, postContent),
                    cause = currentState.cause
                )
            }
            is EditPostUiEvent.UpdatePostTitle -> {
                val postTitle = getNewTitle(event.title)
                _uiState.value = EditPostUiState.FailedAddingPost(
                    postTitle = postTitle,
                    postContent = currentState.postContent,
                    isPostingEnabled = isPostingEnabled(postTitle, currentState.postContent),
                    cause = currentState.cause
                )
            }
        }
    }

    private fun reduce(event: EditPostUiEvent, currentState: EditPostUiState.WaitingPostData) {
        when (event) {
            is EditPostUiEvent.BackClicked -> args.navs.onBackClicked()
            is EditPostUiEvent.EditPost -> editPost(currentState.postTitle, currentState.postContent)
            is EditPostUiEvent.UpdatePostContent -> {
                val postContent = getNewContent(event.content)
                _uiState.value = EditPostUiState.WaitingPostData(
                    postTitle = currentState.postTitle,
                    postContent = postContent,
                    isPostingEnabled = isPostingEnabled(currentState.postTitle, postContent)
                )
            }
            is EditPostUiEvent.UpdatePostTitle -> {
                val postTitle = getNewTitle(event.title)
                _uiState.value = EditPostUiState.WaitingPostData(
                    postTitle = postTitle,
                    postContent = currentState.postContent,
                    isPostingEnabled = isPostingEnabled(postTitle, currentState.postContent),
                )
            }
        }
    }

    private fun isPostingEnabled(postTitle: InputFiled, postContent: InputFiled): Boolean {
        return (postTitle.error == null) and
                postTitle.value.isNotEmpty() and

               (postContent.error == null) and
                postContent.value.isNotEmpty()
    }

    private fun editPost(postTitle: InputFiled, postContent: InputFiled) {
        viewModelScope.launch {
            postRepository.editPost(groupCode = args.groupId, postId = args.postId, title = postTitle.value, description = postContent.value)
                .onResult(
                    onSuccess = { args.navs.onCreatePostClicked(it.success.id) },
                    onFailure = {
                        _uiState.value = EditPostUiState.FailedAddingPost(
                            postTitle = postTitle,
                            postContent = postContent,
                            isPostingEnabled = false,
                            cause = it.cause.message
                        )
                    }
                )
        }
    }

    private fun getNewTitle(code: String): InputFiled {
        return InputFiled(
            value = code,
            error = nameLinter.check(code.trim())
        )
    }

    private fun getNewContent(code: String): InputFiled {
        return InputFiled(
            value = code,
            error = postContentLinter.check(code.trim())
        )
    }
}
