package ua.university.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.university.ui.item.UiText

@Composable
fun BoxScope.CubitBottomButtons(
    positiveButtonText: UiText,
    onPositiveButtonClick: () -> Unit,
    negativeButtonText: UiText,
    onNegativeButtonClick: () -> Unit,
    isPositiveButtonEnabled: Boolean = true,
) {
    Column(
        modifier = Modifier.align(Alignment.BottomStart)
    ) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedButton(
                onClick = onNegativeButtonClick,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = negativeButtonText.asString().uppercase(),
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = onPositiveButtonClick,
                enabled = isPositiveButtonEnabled,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = positiveButtonText.asString().uppercase(),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomButtonsPreview() {
    Box {
        CubitBottomButtons(
            positiveButtonText = UiText.DynamicString("register"),
            onPositiveButtonClick = {},
            negativeButtonText = UiText.DynamicString("cancel"),
            onNegativeButtonClick = {},
        )
    }
}