/*
package ua.university.post.add.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.ui.util.InputLinter
import javax.inject.Named

class AddPostViewModel @AssistedInject constructor(
    @Assisted("addPost") private val onPostClicked: () -> Unit,
    @Assisted("back") private val onBackClicked: () -> Unit,
    @Assisted var groupId: String,
    private val groupRepository: GroupRepository,
    @Named("postContentLinter") private val postContentLinter: InputLinter
) : BaseViewModel<AddPostUiEvent, AddPostUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("addPost") onCreateClicked: () -> Unit,
            @Assisted("back") onBackClicked: () -> Unit,
            @Assisted groupId: String,
        ): AddPostViewModel
    }

    override fun createInitialState(): AddPostUiState {
        val postContent = InputFiled("")
        return AddPostUiState.WaitingPostData(
            postContent = postContent,
            isPostingEnabled = isPostingEnabled(postContent)
        )
    }

    override fun handleEvent(event: AddPostUiEvent) {
        when (val currentState = uiState.value) {
            is AddPostUiState.FailedAddingPost -> reduce(event, currentState)
            is AddPostUiState.WaitingPostData -> reduce(event, currentState)
            is AddPostUiState.WaitingResponse -> throw IllegalStateException()
        }.exhaustive
    }

    private fun reduce(event: AddPostUiEvent, currentState: AddPostUiState.FailedAddingPost) {
        when (event) {
            is AddPostUiEvent.BackClicked -> onBackClicked()
            is AddPostUiEvent.AddPost -> createNewPost(currentState.postContent)
            is AddPostUiEvent.UpdatePostContent -> {
                val postContent = getNewContent(event.content)
                _uiState.value = AddPostUiState.FailedAddingPost(
                    postContent = postContent,
                    isPostingEnabled = isPostingEnabled(postContent),
                    cause = currentState.cause
                )
            }
        }.exhaustive
    }

    private fun reduce(event: AddPostUiEvent, currentState: AddPostUiState.WaitingPostData) {
        when (event) {
            is AddPostUiEvent.BackClicked -> onBackClicked()
            is AddPostUiEvent.AddPost -> createNewPost(currentState.postContent)
            is AddPostUiEvent.UpdatePostContent -> {
                val postContent = getNewContent(event.content)
                _uiState.value = AddPostUiState.WaitingPostData(
                    postContent = postContent,
                    isPostingEnabled = isPostingEnabled(postContent)
                )
            }
        }.exhaustive
    }

    private fun isPostingEnabled(postContent: InputFiled): Boolean {
        return (postContent.error == null) and
                postContent.value.isNotEmpty()
    }

    private fun createNewPost(postContent: InputFiled) {
        viewModelScope.launch {
            groupRepository.createPost(groupCode = groupId, content = postContent.value).result(
                onSuccess = { onPostClicked() },
                onFailure = {
                    _uiState.value = AddPostUiState.FailedAddingPost(
                        postContent = postContent,
                        isPostingEnabled = isPostingEnabled(postContent),
                        cause = it.error.readableCause()
                    )
                }
            )
        }
    }

    private fun getNewContent(code: String): InputFiled {
        return InputFiled(
            value = code,
            error = postContentLinter.check(code.trim())
        )
    }

}*/
