package com.example.feature_user.signUp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_user.R
import com.example.feature_user.common.presentation.composable.BottomButtons
import com.example.feature_user.signUp.presentation.composable.Fields

@Composable
fun SingUpScreen(viewModel: SignUpViewModel) {
    val uiState by viewModel.uiState

    Box(Modifier.fillMaxSize()) {
        Fields(
            uiState = uiState,
            viewModel = viewModel,
        )

        BottomButtons(
            positiveButtonText = stringResource(R.string.register),
            onPositiveButtonClick = { viewModel.handleEvent(event = SignUpUiEvent.SignUp) },
            negativeButtonText = stringResource(R.string.register),
            onNegativeButtonClick = { throw IllegalStateException() },
            isPositiveButtonEnabled = true,
            isNegativeButtonVisible = false,
        )
    }
}