package ua.university.group.list.mvi

import ua.university.group.list.item.GroupItem
import ua.university.ui.item.Reloadable

internal data class State(
    val groups: Reloadable<List<GroupItem>>
)