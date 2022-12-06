package ua.university.auth.ui.log_in.screen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.ui.composable.CubitTextField
import ua.university.ui.item.UiText
import ua.university.auth.R
import ua.university.auth.ui.log_in.mvi.LogInEvent
import ua.university.auth.ui.log_in.mvi.LogInState

@Composable
fun Fields(
    uiState: LogInState,
    eventHandler: (LogInEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // user email TextField
        CubitTextField(
            field = uiState.user.email,
            label = UiText.StringResource(R.string.user_email),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue -> eventHandler(LogInEvent.UpdateUserEmail(newValue)) }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user password TextField
        CubitTextField(
            field = uiState.user.password,
            label = UiText.StringResource(R.string.user_password),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = { newValue -> eventHandler(LogInEvent.UpdateUserPassword(newValue)) }
        )
    }
}