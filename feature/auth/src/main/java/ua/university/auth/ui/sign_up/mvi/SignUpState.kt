package ua.university.auth.ui.sign_up.mvi

import ua.university.auth.ui.sign_up.item.SignUpItem
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText
import ua.university.ui.mvi.UiState

sealed class SignUpState(
    val user: SignUpItem,
    val isSignUpEnabled: Boolean
) : UiState {

    class WaitingUserData(user: SignUpItem, isSignUpEnabled: Boolean)
        : SignUpState(user = user, isSignUpEnabled = isSignUpEnabled)

    class WaitingResponse(user: SignUpItem) : SignUpState(user = user, false)

    class FailedSignUp(user: SignUpItem, val cause: UiText?, isSignUpEnabled: Boolean)
        : SignUpState(user = user, isSignUpEnabled = isSignUpEnabled)
}