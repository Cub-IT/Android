package ua.university.post.ui.add.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.network.result.onResult
import ua.university.post.data.repository.PostRepository
import ua.university.post.ui.add.screen.AddPostScreenArgs
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class AddPostViewModel @AssistedInject constructor(
    @Assisted private val args: AddPostScreenArgs,
    private val postRepository: PostRepository,
    @Named("nameLinter") private val nameLinter: InputLinter,
    @Named("postContentLinter") private val postContentLinter: InputLinter,
) : BaseViewModel<AddPostUiEvent, AddPostUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: AddPostScreenArgs): AddPostViewModel
    }

    override fun createInitialState(): AddPostUiState {
        val postTitle = InputFiled("")
        val postContent = InputFiled("")
        return AddPostUiState.WaitingPostData(
            postTitle = postTitle,
            postContent = postContent,
            isPostingEnabled = isPostingEnabled(postTitle, postContent)
        )
    }

    override fun handleEvent(event: AddPostUiEvent) {
        when (val currentState = uiState.value) {
            is AddPostUiState.FailedAddingPost -> reduce(event, currentState)
            is AddPostUiState.WaitingPostData -> reduce(event, currentState)
            is AddPostUiState.WaitingResponse -> throw IllegalStateException()
        }
    }

    private fun reduce(event: AddPostUiEvent, currentState: AddPostUiState.FailedAddingPost) {
        when (event) {
            is AddPostUiEvent.BackClicked -> args.navs.onBackClicked()
            is AddPostUiEvent.AddPost -> createNewPost(currentState.postTitle, currentState.postContent)
            is AddPostUiEvent.UpdatePostContent -> {
                val postContent = getNewContent(event.content)
                _uiState.value = AddPostUiState.FailedAddingPost(
                    postTitle = currentState.postTitle,
                    postContent = postContent,
                    isPostingEnabled = isPostingEnabled(currentState.postTitle, postContent),
                    cause = currentState.cause
                )
            }
            is AddPostUiEvent.UpdatePostTitle -> {
                val postTitle = getNewContent(event.title)
                _uiState.value = AddPostUiState.FailedAddingPost(
                    postTitle = postTitle,
                    postContent = currentState.postContent,
                    isPostingEnabled = isPostingEnabled(postTitle, currentState.postContent),
                    cause = currentState.cause
                )
            }
        }
    }

    private fun reduce(event: AddPostUiEvent, currentState: AddPostUiState.WaitingPostData) {
        when (event) {
            is AddPostUiEvent.BackClicked -> args.navs.onBackClicked()
            is AddPostUiEvent.AddPost -> createNewPost(currentState.postTitle, currentState.postContent)
            is AddPostUiEvent.UpdatePostContent -> {
                val postContent = getNewContent(event.content)
                _uiState.value = AddPostUiState.WaitingPostData(
                    postTitle = currentState.postTitle,
                    postContent = postContent,
                    isPostingEnabled = isPostingEnabled(currentState.postTitle, postContent)
                )
            }
            is AddPostUiEvent.UpdatePostTitle -> {
                val postTitle = getNewContent(event.title)
                _uiState.value = AddPostUiState.WaitingPostData(
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

    private fun createNewPost(postTitle: InputFiled, postContent: InputFiled) {
        viewModelScope.launch {
            postRepository.createPost(groupCode = args.groupId, title = postTitle.value, description = postContent.value)
                .onResult(
                    onSuccess = { args.navs.onCreatePostClicked(it.success.id) },
                    onFailure = {
                        _uiState.value = AddPostUiState.FailedAddingPost(
                            postTitle = postTitle,
                            postContent = postContent,
                            isPostingEnabled = isPostingEnabled(postTitle, postContent),
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
