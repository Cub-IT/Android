package ua.university.auth.sign_up

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.sign_up.composable.Fields
import ua.university.ui.composable.CubitBottomButtons
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.util.viewModel

@Composable
fun SingUpScreen(
    creator: () -> SignUpViewModel,
    viewModel: SignUpViewModel = viewModel(creator)
) {
    val uiState by viewModel.uiState

    Box(Modifier.fillMaxSize()) {
        if (uiState is SignUpUiState.WaitingResponse) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp).align(Alignment.TopCenter))
        }

        if (uiState is SignUpUiState.FailedSignUp) {
            CubitErrorMessage(
                errorCause = (uiState as SignUpUiState.FailedSignUp).cause,
                modifier = Modifier.padding(16.dp)
            )
        }

        Fields(
            uiState = uiState,
            viewModel = viewModel,
        )

        CubitBottomButtons(
            positiveButtonText = stringResource(R.string.sign_up),
            onPositiveButtonClick = { viewModel.handleEvent(event = SignUpUiEvent.SignUp) },
            negativeButtonText = stringResource(R.string.cancel),
            onNegativeButtonClick = { viewModel.handleEvent(event = SignUpUiEvent.NavigateToSignIn) },
            isPositiveButtonEnabled = uiState.isSignUpEnabled,
            isNegativeButtonVisible = true,
        )
    }
}