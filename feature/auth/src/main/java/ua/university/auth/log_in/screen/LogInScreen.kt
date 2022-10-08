package ua.university.auth.log_in.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.log_in.item.LogInItem
import ua.university.auth.log_in.mvi.State
import ua.university.auth.log_in.screen.composable.Fields
import ua.university.ui.composable.CubitBottomButtons
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.InputFiled
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText

@Composable
fun LogInScreen(args: LogInScreenArgs) {
    val state = State(user = Reloadable(
        value = LogInItem(
            InputFiled("wrong email", UiText.DynamicString("wrong email format")),
            InputFiled("my_pass_word"),
        ),
        status = Reloadable.Status.Idle,
    ))

    Box(Modifier.fillMaxSize()) {
        Fields(user = state.user.value)

        CubitBottomButtons(
            positiveButtonText = UiText.StringResource(R.string.log_in),
            onPositiveButtonClick = { },
            negativeButtonText = UiText.StringResource(R.string.sign_up),
            onNegativeButtonClick = { args.navs.onSignUpClicked() },
            isPositiveButtonEnabled = state.isLogInEnabled,
        )

        if (state.user.status is Reloadable.Status.Error) {
            CubitErrorMessage(
                errorCause = (state.user.status as Reloadable.Status.Error).reason,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (state.user.status is Reloadable.Status.Loading) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.75F)
            ) { }
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}