package com.example.feature_user.signIn.presentation

import com.example.core.presentation.BaseViewModel
import com.example.core.util.exhaustive
import com.example.feature_user.signIn.presentation.item.UserSignInItem

class SignInViewModel : BaseViewModel<SignInUiEvent, SignInUiState>() {

    override fun createInitialState(): SignInUiState {
        return SignInUiState.WaitingUserData(
            user = UserSignInItem(
                email = "",
                password = "",
            )
        )
    }

    override fun handleEvent(event: SignInUiEvent) {
        when (val currentState = _uiState.value) {
            is SignInUiState.FailedSignIn -> reduce(event, currentState)
            is SignInUiState.WaitingResponse -> reduce(event, currentState)
            is SignInUiState.WaitingUserData -> reduce(event, currentState)
        }.exhaustive
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.FailedSignIn) {
        when (event) {
            is SignInUiEvent.SignIn -> signIn(user = currentState.user)
            is SignInUiEvent.UpdateUserLogInData -> {
                _uiState.value = SignInUiState.FailedSignIn(
                    user = currentState.user,
                    cause = currentState.cause,
                )
            }
        }.exhaustive
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.WaitingResponse) {
        when (event) {
            is SignInUiEvent.SignIn,
            is SignInUiEvent.UpdateUserLogInData -> throw IllegalStateException()
        }.exhaustive
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.WaitingUserData) {
        when (event) {
            is SignInUiEvent.SignIn -> signIn(user = currentState.user)
            is SignInUiEvent.UpdateUserLogInData -> {
                _uiState.value = SignInUiState.WaitingUserData(user = currentState.user)
            }
        }.exhaustive
    }

    private fun signIn(user: UserSignInItem) {
        // TODO
    }

}