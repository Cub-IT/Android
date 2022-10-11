package ua.university.group.add.screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.group.R
import ua.university.group.add.mvi.State
import ua.university.ui.composable.CubitTextField
import ua.university.ui.item.UiText

@Composable
fun Fields(
    uiState: State
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        CubitTextField( // group name TextField
            field = uiState.group.value.name,
            onValueChange = { newValue -> },
            modifier = Modifier.fillMaxWidth(),
            label = UiText.StringResource(R.string.group_name),
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CubitTextField( // group description TextField
            field = uiState.group.value.description,
            onValueChange = { newValue -> },
            modifier = Modifier.fillMaxWidth(),
            label = UiText.StringResource(R.string.group_description),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
            singleLine = false,
        )
    }
}