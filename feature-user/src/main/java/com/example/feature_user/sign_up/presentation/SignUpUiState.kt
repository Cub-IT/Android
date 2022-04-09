package com.example.feature_user.sign_up.presentation

import com.example.core.presentation.UiState
import com.example.feature_user.sign_up.presentation.item.UserRegistrationItem

sealed class SignUpUiState(val user: UserRegistrationItem) : UiState() {
    class WaitingUserData(user: UserRegistrationItem) : SignUpUiState(user = user)
    class WaitingResponse(user: UserRegistrationItem) : SignUpUiState(user = user)
    class FailedSignUp(user: UserRegistrationItem, val cause: String) : SignUpUiState(user = user)
}