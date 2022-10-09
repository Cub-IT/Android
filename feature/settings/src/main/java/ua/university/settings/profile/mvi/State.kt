package ua.university.settings.profile.mvi

import ua.university.ui.item.Reloadable
import ua.university.user.model.User

data class State(
    val user: Reloadable<User>
)