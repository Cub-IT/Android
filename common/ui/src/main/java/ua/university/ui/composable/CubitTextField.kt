package ua.university.ui.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import ua.university.ui.item.InputFiled
import ua.university.ui.theme.Typography

@Composable
fun CubitTextField(
    field: InputFiled,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onValueChange: (newValue: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = field.value,
            onValueChange = onValueChange,
            isError =  field.error != null,
            singleLine = singleLine,
            maxLines = 5,
            keyboardOptions = keyboardOptions,
            modifier = modifier.fillMaxWidth(),
            label = { Text(text = stringResource(label)) },
            textStyle = Typography.bodyLarge
        )
        field.error?.apply {
            Text(
                text = stringResource(this),
                style = Typography.labelMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}