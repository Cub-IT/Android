package ua.university.auth.log_in.mvi

import ua.university.auth.log_in.item.LogInItem
import ua.university.ui.item.Reloadable

data class State(
    val user: Reloadable<LogInItem>
) {
    val isLogInEnabled: Boolean =
        (user.status is Reloadable.Status.Idle) and

        (user.value.email.error == null) and
        (user.value.email.value.isNotEmpty()) and

        (user.value.password.error == null) and
        (user.value.password.value.isNotEmpty())
}