package com.example.feature_user.sign_in.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_user.R
import com.example.feature_user.common.presentation.composable.BottomButtons
import com.example.feature_user.sign_in.presentation.composable.Fields

@Composable
fun SingInScreen(viewModel: SignInViewModel) {
    val uiState by viewModel.uiState

    Box(Modifier.fillMaxSize()) {
        Fields(
            uiState = uiState,
            viewModel = viewModel,
        )

        BottomButtons(
            positiveButtonText = stringResource(R.string.sign_in),
            onPositiveButtonClick = { viewModel.handleEvent(event = SignInUiEvent.SignIn) },
            negativeButtonText = stringResource(R.string.cancel),
            onNegativeButtonClick = { throw IllegalStateException() },
            isPositiveButtonEnabled = true,
            isNegativeButtonVisible = false,
        )
    }
}