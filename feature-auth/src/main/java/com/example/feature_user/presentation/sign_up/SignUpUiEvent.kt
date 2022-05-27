package com.example.feature_user.presentation.sign_up

import com.example.core.presentation.UiEvent
import com.example.feature_user.presentation.sign_up.item.UserRegistrationItem

sealed class SignUpUiEvent : UiEvent() {
    class UpdateUserRegistrationData(val user: UserRegistrationItem) : SignUpUiEvent()
    object NavigateToSignIn : SignUpUiEvent()
    object SignUp : SignUpUiEvent()
}