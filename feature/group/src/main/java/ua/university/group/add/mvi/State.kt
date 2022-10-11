package ua.university.group.add.mvi

import ua.university.group.add.item.NewGroupItem
import ua.university.ui.item.Reloadable

data class State(
    val group: Reloadable<NewGroupItem>
) {
    val isCreationEnabled: Boolean =
        (group.status is Reloadable.Status.Idle) and

        (group.value.name.error == null) and
        (group.value.name.value.isNotEmpty())
}
