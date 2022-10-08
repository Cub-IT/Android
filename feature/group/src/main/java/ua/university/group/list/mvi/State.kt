package ua.university.group.list.mvi

import ua.university.group.model.Group
import ua.university.ui.item.Reloadable

data class State(
    val groups: Reloadable<List<Group>>
)