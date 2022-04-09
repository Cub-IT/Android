package com.example.feature_user.signUp.presentation

import com.example.core.presentation.BaseViewModel
import com.example.core.util.exhaustive
import com.example.feature_user.signUp.presentation.item.UserRegistrationItem

class SignUpViewModel : BaseViewModel<SignUpUiEvent, SignUpUiState>() {

    override fun createInitialState(): SignUpUiState {
        return SignUpUiState.WaitingUserData(
            user = UserRegistrationItem(
                name = "",
                email = "",
                password = "",
            )
        )
    }

    override fun handleEvent(event: SignUpUiEvent) {
        when (val currentState = uiState.value) {
            is SignUpUiState.WaitingResponse -> reduce(event, currentState)
            is SignUpUiState.WaitingUserData -> reduce(event, currentState)
            is SignUpUiState.FailedSignUp -> reduce(event, currentState)
        }.exhaustive
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.WaitingResponse) {
        when (event) {
            is SignUpUiEvent.UpdateUserRegistrationData,
            is SignUpUiEvent.SignUp -> throw IllegalStateException()
        }.exhaustive
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.WaitingUserData) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.UpdateUserRegistrationData -> {
                _uiState.value = SignUpUiState.WaitingUserData(user = event.user)
            }
        }.exhaustive
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.FailedSignUp) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.UpdateUserRegistrationData -> {
                _uiState.value = SignUpUiState.FailedSignUp(
                    user = event.user,
                    cause = currentState.cause,
                )
            }
        }.exhaustive
    }

    private fun signUp(user: UserRegistrationItem) {
        // TODO
    }

}