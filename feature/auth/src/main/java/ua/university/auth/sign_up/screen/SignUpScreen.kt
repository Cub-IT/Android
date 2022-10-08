package ua.university.auth.sign_up.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.sign_up.item.SignUpItem
import ua.university.auth.sign_up.mvi.State
import ua.university.auth.sign_up.screen.composable.Fields
import ua.university.ui.composable.CubitBottomButtons
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.InputFiled
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText

@Composable
fun SignUpScreen(args: SignUpScreenArgs) {
    val state = State(user = Reloadable(
        value = SignUpItem(
            InputFiled("my_pass_word"),
            InputFiled("my_pass_word"),
            InputFiled("my_pass_word"),
            InputFiled("my_pass_word"),
        ),
        status = Reloadable.Status.Loading
    ) )

    Box(Modifier.fillMaxSize()) {
        Fields(user = state.user.value)

        CubitBottomButtons(
            positiveButtonText = UiText.StringResource(R.string.sign_up),
            onPositiveButtonClick = { },
            negativeButtonText = UiText.StringResource(R.string.log_in),
            onNegativeButtonClick = { args.navs.onSignUpClicked() },
            isPositiveButtonEnabled = state.isSignUpEnabled,
        )

        if (state.user.status is Reloadable.Status.Error) {
            CubitErrorMessage(
                errorCause = (state.user.status as Reloadable.Status.Error).reason,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (state.user.status is Reloadable.Status.Loading) {
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xC0FFFFFF)) { }
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}