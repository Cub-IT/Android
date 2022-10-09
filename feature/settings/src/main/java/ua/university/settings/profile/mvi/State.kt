package ua.university.settings.profile.mvi

import ua.university.settings.profile.item.UserItem
import ua.university.ui.item.Reloadable
data class State(
    val user: Reloadable<UserItem>
)