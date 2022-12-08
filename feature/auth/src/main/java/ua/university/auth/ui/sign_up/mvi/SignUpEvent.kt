package ua.university.auth.ui.sign_up.mvi

import ua.university.ui.mvi.UiEvent

sealed interface SignUpEvent : UiEvent {
    class UpdateUserFirstName(val firstName: String) : SignUpEvent
    class UpdateUserLastName(val lastName: String) : SignUpEvent
    class UpdateUserEmail(val email: String) : SignUpEvent
    class UpdateUserPassword(val password: String) : SignUpEvent
    object NavigateToSignIn : SignUpEvent
    object SignUp : SignUpEvent
}