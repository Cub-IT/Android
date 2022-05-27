package com.example.feature_user.presentation.sign_up.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.feature_user.R
import com.example.feature_user.presentation.sign_up.SignUpUiEvent
import com.example.feature_user.presentation.sign_up.SignUpUiState
import com.example.feature_user.presentation.sign_up.SignUpViewModel
import com.example.feature_user.presentation.sign_up.item.UserRegistrationItem

@Composable
fun Fields(
    uiState: SignUpUiState,
    viewModel: SignUpViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // user name TextField
        OutlinedTextField(
            value = uiState.user.name,
            onValueChange = { newUserName ->
                viewModel.handleEvent(
                    event = SignUpUiEvent.UpdateUserRegistrationData(
                        user = UserRegistrationItem(
                            name = newUserName,
                            email = uiState.user.email,
                            password = uiState.user.password,
                        )
                    )
                )
            },
            label = { Text(text = stringResource(R.string.user_name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(16.dp))
        // user email TextField
        OutlinedTextField(
            value = uiState.user.email,
            onValueChange = { newUserEmail ->
                viewModel.handleEvent(
                    event = SignUpUiEvent.UpdateUserRegistrationData(
                        user = UserRegistrationItem(
                            name = uiState.user.name,
                            email = newUserEmail,
                            password = uiState.user.password,
                        )
                    )
                )
            },
            label = { Text(text = stringResource(R.string.user_email)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(16.dp))
        // user password TextField
        OutlinedTextField(
            value = uiState.user.password,
            onValueChange = { newUserPassword ->
                viewModel.handleEvent(
                    event = SignUpUiEvent.UpdateUserRegistrationData(
                        user = UserRegistrationItem(
                            name = uiState.user.name,
                            email = uiState.user.email,
                            password = newUserPassword,
                        )
                    )
                )
            },
            label = { Text(text = stringResource(R.string.user_password)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}