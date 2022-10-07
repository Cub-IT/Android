package ua.university.auth.sign_up

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SingUpScreen(args: SingUpScreenArgs) {
    Text(text = "SingUpScreen")
    Button(onClick = { args.onLogInClicked }) {
        Text(text = "Log In")
    }
    Button(onClick = { args.onSignUpClicked }) {
        Text(text = "Sign Up")
    }
}