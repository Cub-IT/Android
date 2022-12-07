package ua.university.post.add.mvi

import ua.university.ui.mvi.UiEvent

sealed class AddPostUiEvent : UiEvent {
    class UpdatePostContent(val content: String) : AddPostUiEvent()
    object AddPost : AddPostUiEvent()
    object BackClicked : AddPostUiEvent()
}