package ua.university.group.selected.mvi

import ua.university.group.selected.item.GroupItem
import ua.university.group.selected.item.PostItem
import ua.university.ui.item.Reloadable

internal data class State(
    val group: Reloadable<GroupItem>,
    val posts: Reloadable<List<PostItem>>,
)