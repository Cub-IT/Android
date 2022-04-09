package com.example.feature_user.singUp.presentation

import com.example.core.presentation.UiEvent
import com.example.feature_user.singUp.presentation.item.UserRegistrationItem

sealed class SingUpUiEvent : UiEvent() {
    class UpdateUserRegistrationData(val user: UserRegistrationItem) : SingUpUiEvent()
    object LogIn : SingUpUiEvent()
}