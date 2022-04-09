package com.example.feature_user.signIn.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_user.R
import com.example.feature_user.common.presentation.composable.BottomButtons

@Composable
fun SingInScreen(viewModel: SignInViewModel) {
    Box(Modifier.fillMaxSize()) {
        BottomButtons(
            positiveButtonText = stringResource(R.string.register),
            onPositiveButtonClick = { viewModel.handleEvent(event = SignInUiEvent.SignIn) },
            negativeButtonText = stringResource(R.string.register),
            onNegativeButtonClick = { throw IllegalStateException() },
            isPositiveButtonEnabled = true,
            isNegativeButtonVisible = false,
        )
    }
}