package ua.university.group.ui.edit.screen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.ui.composable.CubitTextField
import ua.university.group.R
import ua.university.group.ui.edit.mvi.EditGroupUiEvent
import ua.university.group.ui.edit.mvi.EditGroupUiState
import ua.university.ui.item.UiText

@Composable
fun Fields(
    uiState: EditGroupUiState,
    handleEvent: (EditGroupUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // group name TextField
        CubitTextField(
            field = uiState.group.name,
            label = UiText.StringResource(R.string.group_name),
            onValueChange = { newValue ->
                handleEvent(EditGroupUiEvent.UpdateGroupName(newValue))
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // group description TextField
        CubitTextField(
            field = uiState.group.description,
            label = UiText.StringResource(R.string.group_description),
            singleLine = false,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
            onValueChange = { newValue ->
                handleEvent(EditGroupUiEvent.UpdateGroupDescription(newValue))
            }
        )
    }
}