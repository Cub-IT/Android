package com.example.feature_user.presentation.sign_in

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_user.R
import com.example.feature_user.presentation.common.composable.BottomButtons
import com.example.feature_user.presentation.sign_in.composable.Fields
import javax.inject.Inject

@Composable
fun SingInScreen(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    when (uiState) {
        is SignInUiState.FailedSignIn,
        is SignInUiState.WaitingUserData -> {
            Box(Modifier.fillMaxSize()) {
                if (uiState is SignInUiState.FailedSignIn) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = (uiState as SignInUiState.FailedSignIn).cause)
                    }
                }

                Fields(
                    uiState = uiState,
                    viewModel = viewModel,
                )

                BottomButtons(
                    positiveButtonText = stringResource(R.string.sign_in),
                    onPositiveButtonClick = { viewModel.handleEvent(event = SignInUiEvent.SignIn) },
                    negativeButtonText = stringResource(R.string.sign_up),
                    onNegativeButtonClick = { viewModel.handleEvent(event = SignInUiEvent.NavigateToSignUp) },
                    isPositiveButtonEnabled = uiState.isSignInEnabled,
                    isNegativeButtonVisible = true,
                )
            }
        }
        is SignInUiState.WaitingResponse -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}