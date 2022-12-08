package ua.university.auth.ui.sign_up.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.ui.log_in.mvi.LogInViewModel
import ua.university.auth.ui.log_in.screen.LogInScreenArgs
import ua.university.auth.ui.sign_up.item.SignUpItem
import ua.university.auth.ui.sign_up.mvi.SignUpEvent
import ua.university.auth.ui.sign_up.mvi.SignUpState
import ua.university.auth.ui.sign_up.mvi.SignUpViewModel
import ua.university.auth.ui.sign_up.screen.composable.Fields
import ua.university.ui.composable.CubitBottomButtons
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.FullscreenProgressIndicator
import ua.university.ui.item.InputFiled
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText

@Composable
fun SignUpScreen(
    args: SignUpScreenArgs,
    factory: SignUpViewModel.Factory,
    viewModel: SignUpViewModel = ua.university.ui.util.viewModel { factory.create(args) },
) {
    SignUpScreen(
        uiState = viewModel.uiState.value,
        handleEvent = viewModel::handleEvent
    )
}
@Composable
fun SignUpScreen(
    uiState: SignUpState,
    handleEvent: (SignUpEvent) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        if (uiState is SignUpState.WaitingResponse) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp).align(Alignment.TopCenter))
        }

        if (uiState is SignUpState.FailedSignUp) {
            CubitErrorMessage(
                errorCause = uiState.cause,
                modifier = Modifier.padding(16.dp)
            )
        }

        Fields(
            uiState = uiState,
            handleEvent = handleEvent,
        )

        CubitBottomButtons(
            positiveButtonText = UiText.StringResource(R.string.sign_up),
            onPositiveButtonClick = { handleEvent(SignUpEvent.SignUp) },
            negativeButtonText = UiText.StringResource(R.string.cancel),
            onNegativeButtonClick = { handleEvent(SignUpEvent.NavigateToSignIn) },
            isPositiveButtonEnabled = uiState.isSignUpEnabled,
        )
    }
}