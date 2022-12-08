package ua.university.post.ui.edit.mvi

import ua.university.ui.mvi.UiEvent

sealed class EditPostUiEvent : UiEvent {
    class UpdatePostTitle(val title: String) : EditPostUiEvent()
    class UpdatePostContent(val content: String) : EditPostUiEvent()
    object EditPost : EditPostUiEvent()
    object BackClicked : EditPostUiEvent()
}