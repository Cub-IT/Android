package ua.university.auth.log_in

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LogInScreen(args: LogInScreenArgs) {
    Column {
        Text(text = "LogInScreen")
        Button(onClick = { args.onLogInClicked }) {
            Text(text = "Log In")
        }
        Button(onClick = { args.onSignUpClicked }) {
            Text(text = "Sign Up")
        }
    }

}