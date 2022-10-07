package ua.university.group.selected

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SelectedScreen(args: SelectedScreenArgs) {
    Column {
        Text(text = "SelectedScreen")
        Text(text = "group id = ${args.groupId}")
        Button(onClick = { args.onBackClicked }) {
            Text(text = "Back")
        }
        Button(onClick = { args.onUserAvatarClicked }) {
            Text(text = "User Avatar")
        }
    }
}