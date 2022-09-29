package ua.university.auth.log_in

import ua.university.ui.mvi.UiEvent

sealed class SignInUiEvent : UiEvent() {
    class UpdateUserEmail(val email: String) : SignInUiEvent()
    class UpdateUserPassword(val password: String) : SignInUiEvent()
    object SignIn : SignInUiEvent()
    object NavigateToSignUp : SignInUiEvent()
}