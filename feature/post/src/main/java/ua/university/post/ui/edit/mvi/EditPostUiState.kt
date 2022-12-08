package ua.university.post.ui.edit.mvi

import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.UiState

sealed class EditPostUiState(
    val postTitle: InputFiled,
    val postContent: InputFiled,
    val isPostingEnabled: Boolean
) : UiState {

    class WaitingPostData(postTitle: InputFiled, postContent: InputFiled, isPostingEnabled: Boolean)
        : EditPostUiState(postTitle = postTitle, postContent = postContent, isPostingEnabled = isPostingEnabled)

    class WaitingResponse(postTitle: InputFiled, postContent: InputFiled)
        : EditPostUiState(postTitle = postTitle, postContent = postContent, isPostingEnabled = false)

    class FailedAddingPost(postTitle: InputFiled, postContent: InputFiled, isPostingEnabled: Boolean, val cause: String?)
        : EditPostUiState(postTitle = postTitle, postContent = postContent, isPostingEnabled = isPostingEnabled)
}