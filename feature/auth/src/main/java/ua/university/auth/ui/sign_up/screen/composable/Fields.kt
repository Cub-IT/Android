package ua.university.auth.ui.sign_up.screen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.auth.ui.sign_up.item.SignUpItem
import ua.university.ui.composable.CubitTextField
import ua.university.auth.R
import ua.university.auth.ui.sign_up.mvi.SignUpEvent
import ua.university.auth.ui.sign_up.mvi.SignUpState
import ua.university.ui.item.UiText

@Composable
fun Fields(
    uiState: SignUpState,
    handleEvent: (SignUpEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .absolutePadding(top = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // user first name TextField
        CubitTextField(
            field = uiState.user.firstName,
            label = UiText.StringResource(R.string.user_first_name),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue -> handleEvent(SignUpEvent.UpdateUserFirstName(newValue)) }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user first name TextField
        CubitTextField(
            field = uiState.user.lastName,
            label = UiText.StringResource(R.string.user_last_name),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue -> handleEvent(SignUpEvent.UpdateUserLastName(newValue)) }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user email TextField
        CubitTextField(
            field = uiState.user.email,
            label = UiText.StringResource(R.string.user_email),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue -> handleEvent(SignUpEvent.UpdateUserEmail(newValue)) }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user password TextField
        CubitTextField(
            field = uiState.user.password,
            label = UiText.StringResource(R.string.user_password),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = { newValue -> handleEvent(SignUpEvent.UpdateUserPassword(newValue)) }
        )
    }
}