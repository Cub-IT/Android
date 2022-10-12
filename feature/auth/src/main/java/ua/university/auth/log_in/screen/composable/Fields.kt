package ua.university.auth.log_in.screen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.auth.log_in.item.LogInItem
import ua.university.ui.composable.CubitTextField
import ua.university.ui.item.UiText
import ua.university.auth.R

@Composable
fun Fields(
    user: LogInItem,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CubitTextField( // user email TextField
            field = user.email,
            onValueChange = { newValue -> },
            label = UiText.StringResource(R.string.user_email),
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CubitTextField( // user password TextField
            field = user.password,
            onValueChange = { newValue -> },
            label = UiText.StringResource(R.string.user_password),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }
}