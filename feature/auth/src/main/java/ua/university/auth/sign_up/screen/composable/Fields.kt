package ua.university.auth.sign_up.screen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.auth.sign_up.item.SignUpItem
import ua.university.ui.composable.CubitTextField
import ua.university.auth.R
import ua.university.ui.item.UiText

@Composable
fun Fields(
    user: SignUpItem
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .absolutePadding(top = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CubitTextField( // user first name TextField
            field = user.firstName,
            onValueChange = { newValue -> },
            label = UiText.StringResource(R.string.user_first_name),
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CubitTextField( // user first name TextField
            field = user.lastName,
            onValueChange = { newValue -> },
            label = UiText.StringResource(R.string.user_last_name),
        )

        Spacer(modifier = Modifier.padding(16.dp))

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