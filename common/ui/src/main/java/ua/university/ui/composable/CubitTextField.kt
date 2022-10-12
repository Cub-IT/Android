package ua.university.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import ua.university.ui.item.InputFiled
import ua.university.ui.item.UiText
import ua.university.ui.theme.CubITTheme
import ua.university.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CubitTextField(
    field: InputFiled,
    onValueChange: (newValue: String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    label: UiText? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = field.value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { label?.let { Text(text = label.asString()) } },
        supportingText = {
            field.error?.let {
                Text(
                    text = it.asString(),
                    style = Typography.labelMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        isError = field.error != null,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = 5,
    )
}

@Preview(showBackground = true)
@Composable
fun CubitTextFieldPreview_NoError() {
    CubITTheme {
        CubitTextField(
            field = InputFiled(value = "Text example"),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CubitTextFieldPreview_Error() {
    CubITTheme {
        CubitTextField(
            field = InputFiled(value = "Text example", error = UiText.DynamicString("Error example")),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}