package ua.university.auth.sign_up

import ua.university.auth.sign_up.item.UserRegistrationItem
import ua.university.ui.mvi.UiState

sealed class SignUpUiState(
    val user: UserRegistrationItem,
    val isSignUpEnabled: Boolean
) : UiState() {

    class WaitingUserData(user: UserRegistrationItem, isSignUpEnabled: Boolean)
        : SignUpUiState(user = user, isSignUpEnabled = isSignUpEnabled)

    class WaitingResponse(user: UserRegistrationItem) : SignUpUiState(user = user, false)

    class FailedSignUp(user: UserRegistrationItem, val cause: String?, isSignUpEnabled: Boolean)
        : SignUpUiState(user = user, isSignUpEnabled = isSignUpEnabled)
}