package ua.university.auth.sign_up.mvi

import ua.university.auth.sign_up.item.SignUpItem
import ua.university.ui.item.Reloadable

data class State(
    val user: Reloadable<SignUpItem>
) {
    val isSignUpEnabled: Boolean =
        (user.status is Reloadable.Status.Idle) and

        (user.value.firstName.error == null) and
        (user.value.firstName.value.isNotEmpty()) and

        (user.value.lastName.error == null) and
        (user.value.lastName.value.isNotEmpty()) and

        (user.value.email.error == null) and
        (user.value.email.value.isNotEmpty()) and

        (user.value.password.error == null) and
        (user.value.password.value.isNotEmpty())
}