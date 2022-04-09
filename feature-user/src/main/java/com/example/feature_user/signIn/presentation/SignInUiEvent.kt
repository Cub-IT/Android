package com.example.feature_user.signIn.presentation

import com.example.core.presentation.UiEvent
import com.example.feature_user.signIn.presentation.item.UserSignInItem

sealed class SignInUiEvent : UiEvent() {
    class UpdateUserLogInData(val user: UserSignInItem) : SignInUiEvent()
    object SignIn : SignInUiEvent()
}