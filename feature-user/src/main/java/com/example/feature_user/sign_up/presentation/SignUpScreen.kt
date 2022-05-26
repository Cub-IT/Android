package com.example.feature_user.sign_up.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_user.R
import com.example.feature_user.common.presentation.composable.BottomButtons
import com.example.feature_user.sign_up.presentation.composable.Fields

@Composable
fun SingUpScreen(viewModel: SignUpViewModel) {
    val uiState by viewModel.uiState

    when (uiState) {

        is SignUpUiState.FailedSignUp,
        is SignUpUiState.WaitingUserData -> {
            Box(Modifier.fillMaxSize()) {
                if (uiState is SignUpUiState.FailedSignUp) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = (uiState as SignUpUiState.FailedSignUp).cause)
                    }
                }

                Fields(
                    uiState = uiState,
                    viewModel = viewModel,
                )

                BottomButtons(
                    positiveButtonText = stringResource(R.string.sign_in),
                    onPositiveButtonClick = { viewModel.handleEvent(event = SignUpUiEvent.SignUp) },
                    negativeButtonText = stringResource(R.string.sign_up),
                    onNegativeButtonClick = { viewModel.handleEvent(event = SignUpUiEvent.NavigateToSignIn) },
                    isPositiveButtonEnabled = uiState.isSignUpEnabled,
                    isNegativeButtonVisible = true,
                )
            }
        }
        is SignUpUiState.WaitingResponse -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }








    Box(Modifier.fillMaxSize()) {
        Fields(
            uiState = uiState,
            viewModel = viewModel,
        )

        BottomButtons(
            positiveButtonText = stringResource(R.string.sign_up),
            onPositiveButtonClick = { viewModel.handleEvent(event = SignUpUiEvent.SignUp) },
            negativeButtonText = stringResource(R.string.cancel),
            onNegativeButtonClick = { throw IllegalStateException() },
            isPositiveButtonEnabled = true,
            isNegativeButtonVisible = true,
        )
    }
}