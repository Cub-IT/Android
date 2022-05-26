package com.example.feature_user.sign_up.presentation

import com.example.core.presentation.UiEvent
import com.example.feature_user.sign_up.presentation.item.UserRegistrationItem

sealed class SignUpUiEvent : UiEvent() {
    class UpdateUserRegistrationData(val user: UserRegistrationItem) : SignUpUiEvent()
    object NavigateToSignIn : SignUpUiEvent()
    object SignUp : SignUpUiEvent()
}