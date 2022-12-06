package ua.university.auth.ui.log_in.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.ui.log_in.mvi.LogInEvent
import ua.university.auth.ui.log_in.mvi.LogInState
import ua.university.auth.ui.log_in.mvi.LogInViewModel
import ua.university.auth.ui.log_in.screen.composable.Fields
import ua.university.ui.composable.CubitBottomButtons
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.UiText
import ua.university.ui.util.viewModel

@Composable
fun LogInScreen(
    args: LogInScreenArgs,
    factory: LogInViewModel.Factory,
    viewModel: LogInViewModel = viewModel { factory.create(args) },
) {
    LogInScreen(
        state = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun LogInScreen(
    state: LogInState,
    handleEvent: (LogInEvent) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        if (state is LogInState.WaitingResponse) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp).align(Alignment.TopCenter))
        }

        if (state is LogInState.FailedSignIn) {
            CubitErrorMessage(
                errorCause = state.cause,
                modifier = Modifier.padding(16.dp)
            )
        }

        Fields(
            uiState = state,
            eventHandler = handleEvent,
        )

        CubitBottomButtons(
            positiveButtonText = UiText.StringResource(R.string.log_in),
            onPositiveButtonClick = { handleEvent(LogInEvent.SignIn) },
            negativeButtonText = UiText.StringResource(R.string.sign_up),
            onNegativeButtonClick = { handleEvent(LogInEvent.NavigateToSignUp) },
            isPositiveButtonEnabled = state.isSignInEnabled,
        )
    }
}