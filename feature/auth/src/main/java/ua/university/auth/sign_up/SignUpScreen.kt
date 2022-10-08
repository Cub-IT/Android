package ua.university.auth.sign_up

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(args: SignUpScreenArgs) {
    Column {
        Text(text = "SingUpScreen")
        Button(onClick = { args.navs.onLogInClicked() }) {
            Text(text = "Log In")
        }
        Button(onClick = { args.navs.onSignUpClicked() }) {
            Text(text = "Sign Up")
        }
    }
}