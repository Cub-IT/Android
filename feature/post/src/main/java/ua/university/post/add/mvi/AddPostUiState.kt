package ua.university.post.add.mvi

import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.UiState

sealed class AddPostUiState(
    val postContent: InputFiled,
    val isPostingEnabled: Boolean
) : UiState {

    class WaitingPostData(postContent: InputFiled, isPostingEnabled: Boolean)
        : AddPostUiState(postContent = postContent, isPostingEnabled = isPostingEnabled)

    class WaitingResponse(postContent: InputFiled)
        : AddPostUiState(postContent = postContent, isPostingEnabled = false)

    class FailedAddingPost(postContent: InputFiled, isPostingEnabled: Boolean, val cause: String?)
        : AddPostUiState(postContent = postContent, isPostingEnabled = isPostingEnabled)
}