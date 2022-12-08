package ua.university.post.ui.selected.mvi

import ua.university.post.ui.selected.item.PostItem
import ua.university.ui.item.Reloadable

data class State(
    val post: Reloadable<PostItem>
)
