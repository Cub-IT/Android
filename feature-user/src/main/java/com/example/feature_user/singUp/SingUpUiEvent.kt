package com.example.feature_user.singUp

import com.example.core.presentation.UiEvent
import com.example.feature_user.singUp.item.UserRegistrationItem

sealed class SingUpUiEvent : UiEvent() {
    class LogIn(val user: UserRegistrationItem) : SingUpUiEvent()
}