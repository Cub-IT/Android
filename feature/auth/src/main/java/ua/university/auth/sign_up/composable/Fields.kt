package ua.university.auth.sign_up.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.auth.R
import ua.university.auth.sign_up.SignUpUiEvent
import ua.university.auth.sign_up.SignUpUiState
import ua.university.auth.sign_up.SignUpViewModel
import ua.university.ui.composable.CubitTextField

@Composable
fun Fields(
    uiState: SignUpUiState,
    viewModel: SignUpViewModel,
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
            label = R.string.user_first_name,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue ->
                viewModel.handleEvent(event = SignUpUiEvent.UpdateUserFirstName(newValue))
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user first name TextField
        CubitTextField(
            field = uiState.user.lastName,
            label = R.string.user_last_name,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue ->
                viewModel.handleEvent(event = SignUpUiEvent.UpdateUserLastName(newValue))
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user email TextField
        CubitTextField(
            field = uiState.user.email,
            label = R.string.user_email,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { newValue ->
                viewModel.handleEvent(event = SignUpUiEvent.UpdateUserEmail(newValue))
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // user password TextField
        CubitTextField(
            field = uiState.user.password,
            label = R.string.user_password,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onValueChange = { newValue ->
                viewModel.handleEvent(event = SignUpUiEvent.UpdateUserPassword(newValue))
            }
        )
    }
}