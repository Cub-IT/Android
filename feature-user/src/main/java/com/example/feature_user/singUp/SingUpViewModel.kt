package com.example.feature_user.singUp

import com.example.core.presentation.BaseViewModel
import com.example.core.util.exhaustive
import com.example.feature_user.singUp.item.UserRegistrationItem

class SingUpViewModel : BaseViewModel<SingUpUiEvent, SingUpUiState>() {

    override fun createInitialState(): SingUpUiState {
        return SingUpUiState.WaitingUserData(
            user = UserRegistrationItem(
                name = "",
                email = "",
                password = "",
            )
        )
    }

    override fun handleEvent(event: SingUpUiEvent) {
        when (val currentState = uiState.value) {
            is SingUpUiState.WaitingResponse -> reduce(event, currentState)
            is SingUpUiState.WaitingUserData -> reduce(event, currentState)
            is SingUpUiState.FailedSingUp -> reduce(event, currentState)
        }.exhaustive
    }

    private fun reduce(event: SingUpUiEvent, currentState: SingUpUiState.WaitingResponse) {
        when (event) {
            is SingUpUiEvent.LogIn -> throw IllegalStateException()
        }.exhaustive
    }

    private fun reduce(event: SingUpUiEvent, currentState: SingUpUiState.WaitingUserData) {
        when (event) {
            is SingUpUiEvent.LogIn -> logIn(user = event.user)
        }.exhaustive
    }

    private fun reduce(event: SingUpUiEvent, currentState: SingUpUiState.FailedSingUp) {
        when (event) {
            is SingUpUiEvent.LogIn -> logIn(user = event.user)
        }.exhaustive
    }

    private fun logIn(user: UserRegistrationItem) {
        // TODO
    }

}