package ua.university.group.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListScreen(args: ListScreenArgs) {
    Column {
        Text(text = "ListScreen")
        Button(onClick = { args.navs.onGroupClicked("123456abc") }) {
            Text(text = "onGroupClicked")
        }
        Button(onClick = { args.navs.onUserAvatarClicked() }) {
            Text(text = "User Avatar")
        }
        Button(onClick = { args.navs.onAddGroupClicked() }) {
            Text(text = "Add Group")
        }
        Button(onClick = { args.navs.onJoinGroupClicked() }) {
            Text(text = "Join Group")
        }
    }
}