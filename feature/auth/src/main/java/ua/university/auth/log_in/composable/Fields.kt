package ua.university.auth.log_in.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.log_in.SignInUiEvent
import ua.university.auth.log_in.SignInUiState
import ua.university.auth.log_in.SignInViewModel
import ua.university.ui.composable.CubitTextField

@Composable
fun Fields(
    uiState: SignInUiState,
    viewModel: SignInViewModel,
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
            label = R.string.user_email,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue ->
                viewModel.handleEvent(event = SignInUiEvent.UpdateUserEmail(newValue))
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user password TextField
        CubitTextField(
            field = uiState.user.password,
            label = R.string.user_password,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = { newValue ->
                viewModel.handleEvent(event = SignInUiEvent.UpdateUserPassword(newValue))
            }
        )
    }
}