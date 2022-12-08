package ua.university.post.ui.add.mvi

import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.UiState

sealed class AddPostUiState(
    val postTitle: InputFiled,
    val postContent: InputFiled,
    val isPostingEnabled: Boolean
) : UiState {

    class WaitingPostData(postTitle: InputFiled, postContent: InputFiled, isPostingEnabled: Boolean)
        : AddPostUiState(postTitle = postTitle, postContent = postContent, isPostingEnabled = isPostingEnabled)

    class WaitingResponse(postTitle: InputFiled, postContent: InputFiled)
        : AddPostUiState(postTitle = postTitle, postContent = postContent, isPostingEnabled = false)

    class FailedAddingPost(postTitle: InputFiled, postContent: InputFiled, isPostingEnabled: Boolean, val cause: String?)
        : AddPostUiState(postTitle = postTitle, postContent = postContent, isPostingEnabled = isPostingEnabled)
}