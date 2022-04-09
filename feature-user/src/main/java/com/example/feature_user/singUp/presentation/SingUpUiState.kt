package com.example.feature_user.singUp.presentation

import com.example.core.presentation.UiState
import com.example.feature_user.singUp.presentation.item.UserRegistrationItem

sealed class SingUpUiState(val user: UserRegistrationItem) : UiState() {
    class WaitingUserData(user: UserRegistrationItem) : SingUpUiState(user = user)
    class WaitingResponse(user: UserRegistrationItem) : SingUpUiState(user = user)
    class FailedSingUp(user: UserRegistrationItem, val cause: String) : SingUpUiState(user = user)
}