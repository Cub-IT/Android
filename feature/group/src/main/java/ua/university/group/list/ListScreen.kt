package ua.university.group.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListScreen(args: ListScreenArgs) {
    Column {
        Text(text = "ListScreen")
        Button(onClick = { args.onGroupClicked }) {
            Text(text = "onGroupClicked")
        }
        Button(onClick = { args.onUserAvatarClicked }) {
            Text(text = "User Avatar")
        }
        Button(onClick = { args.onAddGroupClicked }) {
            Text(text = "Add Group")
        }
        Button(onClick = { args.onJoinGroupClicked }) {
            Text(text = "Join Group")
        }
    }
}