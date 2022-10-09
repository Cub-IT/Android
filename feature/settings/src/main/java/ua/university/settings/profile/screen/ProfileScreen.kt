package ua.university.settings.profile.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.university.settings.profile.mvi.State
import ua.university.ui.composable.IconAvatar
import ua.university.ui.item.Reloadable
import ua.university.user.model.previewUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(args: ProfileScreenArgs) {
    val uiState = State(Reloadable(
        value = previewUser(),
        status = Reloadable.Status.Loading
    ))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Profile")
                },
                navigationIcon = {
                    IconButton(onClick = args.navs.onBackClicked) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (uiState.user.status is Reloadable.Status.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconAvatar(color = Color(0xFF3B79E8), size = 96.dp)

                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "${uiState.user.value.firstName} ${uiState.user.value.lastName}")
                Divider(modifier = Modifier.width(32.dp).padding(vertical = 12.dp))
                Text(text = uiState.user.value.email)
                Spacer(modifier = Modifier.padding(64.dp))

                OutlinedButton(onClick = args.navs.onLogoutClicked) {
                    Text(text = "Logout")
                }
            }
        }
    }
}