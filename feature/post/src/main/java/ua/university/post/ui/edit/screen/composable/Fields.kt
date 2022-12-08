package ua.university.post.ui.edit.screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.post.R
import ua.university.post.ui.add.mvi.AddPostUiEvent
import ua.university.post.ui.add.mvi.AddPostUiState
import ua.university.post.ui.edit.mvi.EditPostUiEvent
import ua.university.post.ui.edit.mvi.EditPostUiState
import ua.university.ui.composable.CubitTextField
import ua.university.ui.item.UiText

@Composable
fun Fields(
    uiState: EditPostUiState,
    handleEvent: (EditPostUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CubitTextField( // post title TextField
            field = uiState.postTitle,
            onValueChange = { newValue -> handleEvent(EditPostUiEvent.UpdatePostTitle(newValue)) },
            label = UiText.StringResource(R.string.post_title),
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CubitTextField( // post content TextField
            field = uiState.postContent,
            onValueChange = { newValue -> handleEvent(EditPostUiEvent.UpdatePostContent(newValue)) },
            label = UiText.StringResource(R.string.post_content),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            singleLine = false,
        )
    }
}
