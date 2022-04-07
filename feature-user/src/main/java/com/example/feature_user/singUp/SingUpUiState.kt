package com.example.feature_user.singUp

import com.example.core.presentation.UiState
import com.example.feature_user.singUp.item.UserRegistrationItem

sealed class SingUpUiState : UiState() {
    class WaitingUserData(val user: UserRegistrationItem) : SingUpUiState()
    class WaitingResponse(val user: UserRegistrationItem) : SingUpUiState()
    class FailedSingUp(val user: UserRegistrationItem, val cause: String) : SingUpUiState()
}