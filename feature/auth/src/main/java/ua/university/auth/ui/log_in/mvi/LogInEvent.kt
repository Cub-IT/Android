package ua.university.auth.ui.log_in.mvi

import ua.university.ui.mvi.UiEvent

sealed interface LogInEvent : UiEvent {
    class UpdateUserEmail(val email: String) : LogInEvent
    class UpdateUserPassword(val password: String) : LogInEvent
    object SignIn : LogInEvent
    object NavigateToSignUp : LogInEvent
}