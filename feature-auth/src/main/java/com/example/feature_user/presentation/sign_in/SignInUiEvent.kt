package com.example.feature_user.presentation.sign_in

import com.example.core.presentation.UiEvent
import com.example.feature_user.presentation.sign_in.item.UserSignInItem

sealed class SignInUiEvent : UiEvent() {
    class UpdateUserLogInData(val user: UserSignInItem) : SignInUiEvent()
    object SignIn : SignInUiEvent()
    object NavigateToSignUp : SignInUiEvent()
}