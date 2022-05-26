package com.example.feature_user.sign_up.presentation

import com.example.core.presentation.UiState
import com.example.feature_user.sign_up.presentation.item.UserRegistrationItem

sealed class SignUpUiState(
    val user: UserRegistrationItem,
    val isSignUpEnabled: Boolean
) : UiState() {

    class WaitingUserData(user: UserRegistrationItem, isSignUpEnabled: Boolean)
        : SignUpUiState(user = user, isSignUpEnabled = isSignUpEnabled)

    class WaitingResponse(user: UserRegistrationItem) : SignUpUiState(user = user, false)

    class FailedSignUp(user: UserRegistrationItem, val cause: String, isSignUpEnabled: Boolean)
        : SignUpUiState(user = user, isSignUpEnabled = isSignUpEnabled)
}