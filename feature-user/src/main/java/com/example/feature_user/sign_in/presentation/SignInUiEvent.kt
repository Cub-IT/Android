package com.example.feature_user.sign_in.presentation

import com.example.core.presentation.UiEvent
import com.example.feature_user.sign_in.presentation.item.UserSignInItem

sealed class SignInUiEvent : UiEvent() {
    class UpdateUserLogInData(val user: UserSignInItem) : SignInUiEvent()
    object SignIn : SignInUiEvent()
    object NavigateToSignUp : SignInUiEvent()
}