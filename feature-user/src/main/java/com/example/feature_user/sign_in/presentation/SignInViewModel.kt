package com.example.feature_user.sign_in.presentation

import com.example.core.presentation.BaseViewModel
import com.example.core.util.exhaustive
import com.example.feature_user.sign_in.presentation.item.UserSignInItem

class SignInViewModel : BaseViewModel<SignInUiEvent, SignInUiState>() {

    override fun createInitialState(): SignInUiState {
        val user = UserSignInItem(email = "", password = "")
        return SignInUiState.WaitingUserData(
            user = user,
            isSignInEnabled = isSignInEnabled(user)
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
            is SignInUiEvent.NavigateToSignUp -> TODO()
            is SignInUiEvent.UpdateUserLogInData -> {
                _uiState.value = SignInUiState.FailedSignIn(
                    user = event.user,
                    cause = currentState.cause,
                    isSignInEnabled = isSignInEnabled(event.user)
                )
            }
        }.exhaustive
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.WaitingResponse) {
        when (event) {
            is SignInUiEvent.SignIn,
            is SignInUiEvent.NavigateToSignUp,
            is SignInUiEvent.UpdateUserLogInData -> throw IllegalStateException()
        }.exhaustive
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.WaitingUserData) {
        when (event) {
            is SignInUiEvent.SignIn -> signIn(user = currentState.user)
            is SignInUiEvent.NavigateToSignUp -> TODO()
            is SignInUiEvent.UpdateUserLogInData -> {
                _uiState.value = SignInUiState.WaitingUserData(
                    user = event.user,
                    isSignInEnabled = isSignInEnabled(event.user)
                )
            }
        }.exhaustive
    }

    private fun isSignInEnabled(user: UserSignInItem): Boolean {
        // TODO
        return true
    }

    private fun signIn(user: UserSignInItem) {
        // TODO
    }

}