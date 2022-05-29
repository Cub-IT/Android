package com.example.feature_user.presentation.sign_up

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.AuthRepository
import com.example.core.presentation.BaseViewModel
import com.example.core.util.exhaustive
import com.example.feature_user.presentation.sign_up.item.UserRegistrationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val onSignInClicked: () -> Unit,
    private val onSignUpClicked: () -> Unit,
    private val authRepository: AuthRepository
) : BaseViewModel<SignUpUiEvent, SignUpUiState>() {

    override fun createInitialState(): SignUpUiState {
        val user = UserRegistrationItem(
            name = "",
            email = "",
            password = "",
        )
        return SignUpUiState.WaitingUserData(
            user = user,
            isSignUpEnabled = isSignUpEnabled(user)
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
            is SignUpUiEvent.NavigateToSignIn,
            is SignUpUiEvent.SignUp -> throw IllegalStateException()
        }.exhaustive
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.WaitingUserData) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.NavigateToSignIn -> onSignInClicked()
            is SignUpUiEvent.UpdateUserRegistrationData -> {
                _uiState.value = SignUpUiState.WaitingUserData(
                    user = event.user,
                    isSignUpEnabled = isSignUpEnabled(event.user)
                )
            }
        }.exhaustive
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.FailedSignUp) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.NavigateToSignIn -> onSignInClicked()
            is SignUpUiEvent.UpdateUserRegistrationData -> {
                _uiState.value = SignUpUiState.FailedSignUp(
                    user = event.user,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(event.user)
                )
            }
        }.exhaustive
    }

    private fun isSignUpEnabled(user: UserRegistrationItem): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(user.email).matches()
                && user.password.isNotBlank()
                && user.name.isNotBlank()
    }

    private fun signUp(user: UserRegistrationItem) {
        viewModelScope.launch {
            authRepository.signUp(
                name = user.name,
                email = user.email,
                password = user.password
            )
            onSignUpClicked()
        }
    }

}