package ua.university.auth.sign_up

import ua.university.ui.mvi.UiEvent

sealed class SignUpUiEvent : UiEvent() {
    class UpdateUserFirstName(val firstName: String) : SignUpUiEvent()
    class UpdateUserLastName(val lastName: String) : SignUpUiEvent()
    class UpdateUserEmail(val email: String) : SignUpUiEvent()
    class UpdateUserPassword(val password: String) : SignUpUiEvent()
    object NavigateToSignIn : SignUpUiEvent()
    object SignUp : SignUpUiEvent()
}