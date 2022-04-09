package com.example.feature_user.sign_in.presentation.composable

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
import com.example.feature_user.sign_in.presentation.SignInUiEvent
import com.example.feature_user.sign_in.presentation.SignInUiState
import com.example.feature_user.sign_in.presentation.SignInViewModel
import com.example.feature_user.sign_in.presentation.item.UserSignInItem

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
        OutlinedTextField(
            value = uiState.user.email,
            onValueChange = { newUserEmail ->
                viewModel.handleEvent(
                    event = SignInUiEvent.UpdateUserLogInData(
                        user = UserSignInItem(
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
                    event = SignInUiEvent.UpdateUserLogInData(
                        user = UserSignInItem(
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