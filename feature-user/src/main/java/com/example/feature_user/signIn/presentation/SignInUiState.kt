package com.example.feature_user.signIn.presentation

import com.example.core.presentation.UiState
import com.example.feature_user.signIn.presentation.item.UserSignInItem

sealed class SignInUiState(val user: UserSignInItem) : UiState() {
    class WaitingUserData(user: UserSignInItem) : SignInUiState(user = user)
    class WaitingResponse(user: UserSignInItem) : SignInUiState(user = user)
    class FailedSignIn(user: UserSignInItem, val cause: String) : SignInUiState(user = user)
}