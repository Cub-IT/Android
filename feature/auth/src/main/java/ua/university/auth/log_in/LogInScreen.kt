package ua.university.auth.log_in
/*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.university.auth.log_in.composable.Fields
import ua.university.ui.composable.CubitBottomButtons

@Composable
fun LogInScreen(
    viewModel: LogInViewModel
) {
    val uiState by viewModel.uiState

    Box(Modifier.fillMaxSize()) {
        if (uiState is SignInUiState.WaitingResponse) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp).align(Alignment.TopCenter))
        }

        if (uiState is SignInUiState.FailedSignIn) {
            ErrorMessage(
                errorCause = (uiState as SignInUiState.FailedSignIn).cause,
                modifier = Modifier.padding(16.dp)
            )
        }

        Fields(
            uiState = uiState,
            viewModel = viewModel,
        )

        CubitBottomButtons(
            positiveButtonText = stringResource(R.string.sign_in),
            onPositiveButtonClick = { viewModel.handleEvent(event = SignInUiEvent.SignIn) },
            negativeButtonText = stringResource(R.string.sign_up),
            onNegativeButtonClick = { viewModel.handleEvent(event = SignInUiEvent.NavigateToSignUp) },
            isPositiveButtonEnabled = uiState.isSignInEnabled,
            isNegativeButtonVisible = true,
        )
    }
}*/