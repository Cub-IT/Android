package com.example.feature_user.singUp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_user.R
import com.example.feature_user.singUp.presentation.composable.BottomButtons
import com.example.feature_user.singUp.presentation.composable.Fields

@Composable
fun SingUpScreen(viewModel: SingUpViewModel) {
    val uiState by viewModel.uiState

    Box(Modifier.fillMaxSize()) {
        Fields(
            uiState = uiState,
            viewModel = viewModel,
        )

        BottomButtons(
            positiveButtonText = stringResource(R.string.register),
            onPositiveButtonClick = { viewModel.handleEvent(event = SingUpUiEvent.LogIn) },
            negativeButtonText = stringResource(R.string.register),
            onNegativeButtonClick = { throw IllegalStateException() },
            isPositiveButtonEnabled = true,
            isNegativeButtonVisible = false,
        )
    }
}