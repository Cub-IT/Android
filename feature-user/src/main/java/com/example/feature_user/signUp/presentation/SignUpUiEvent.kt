package com.example.feature_user.signUp.presentation

import com.example.core.presentation.UiEvent
import com.example.feature_user.signUp.presentation.item.UserRegistrationItem

sealed class SignUpUiEvent : UiEvent() {
    class UpdateUserRegistrationData(val user: UserRegistrationItem) : SignUpUiEvent()
    object SignUp : SignUpUiEvent()
}