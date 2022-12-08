package ua.university.post.ui.add.mvi

import ua.university.ui.mvi.UiEvent

sealed class AddPostUiEvent : UiEvent {
    class UpdatePostTitle(val title: String) : AddPostUiEvent()
    class UpdatePostContent(val content: String) : AddPostUiEvent()
    object AddPost : AddPostUiEvent()
    object BackClicked : AddPostUiEvent()
}