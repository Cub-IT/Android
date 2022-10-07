package ua.university.group.members

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MembersScreen(args: MembersScreenArgs) {
    Column {
        Text(text = "MembersScreen")
        Button(onClick = { args.onBackClicked }) {
            Text(text = "Back")
        }
        Button(onClick = { args.onUserAvatarClicked }) {
            Text(text = "User Avatar")
        }
    }
}