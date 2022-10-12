package ua.university.post.selected.mvi

import ua.university.post.selected.item.PostItem
import ua.university.ui.item.Reloadable

data class State(
    val post: Reloadable<PostItem>
)
