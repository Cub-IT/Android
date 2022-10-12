package ua.university.post.add.mvi

import ua.university.post.add.item.NewPostItem
import ua.university.ui.item.Reloadable

data class State(
    val post: Reloadable<NewPostItem>
) {
    val isPostingEnabled = false
}