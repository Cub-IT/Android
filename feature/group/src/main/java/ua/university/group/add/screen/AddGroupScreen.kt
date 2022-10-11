package ua.university.group.add.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.group.add.item.previewNewGroupItem
import ua.university.group.add.mvi.State
import ua.university.group.add.screen.composable.Fields
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.item.Reloadable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGroupScreen(args: AddGroupScreenArgs) {
    val uiState = State(Reloadable(previewNewGroupItem(), Reloadable.Status.Idle))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create group")
                },
                navigationIcon = {
                    IconButton(onClick = { args.navs.onBackClicked() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { args.navs.onCreateGroup },
                        enabled = uiState.isCreationEnabled
                    ) {
                        Text(text = "Create")
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Fields(uiState = uiState)

            if (uiState.group.status is Reloadable.Status.Error) {
                CubitErrorMessage(
                    errorCause = (uiState.group.status as Reloadable.Status.Error).reason,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (uiState.group.status is Reloadable.Status.Loading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.75F)
                ) { }
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}