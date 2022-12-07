package ua.university.post.add.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.university.post.add.item.previewNewPostItem
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.FullscreenProgressIndicator
import ua.university.ui.item.Reloadable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(args: AddPostScreenArgs) {
    /*val uiState = State(Reloadable(value = previewNewPostItem(), status = Reloadable.Status.Idle))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add post")
                },
                navigationIcon = {
                    IconButton(onClick = { args.navs.onBackClicked() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { args.navs.onCreatePostClicked },
                        enabled = uiState.isPostingEnabled
                    ) {
                        Text(text = "Create")
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Fields(uiState = uiState)

            if (uiState.post.status is Reloadable.Status.Error) {
                CubitErrorMessage(
                    errorCause = (uiState.post.status as Reloadable.Status.Error).reason,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (uiState.post.status is Reloadable.Status.Loading) {
                FullscreenProgressIndicator()
            }
        }
    }*/
}