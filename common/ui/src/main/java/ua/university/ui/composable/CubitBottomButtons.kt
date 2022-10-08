package ua.university.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.CubitBottomButtons(
    positiveButtonText: String,
    onPositiveButtonClick: () -> Unit,
    negativeButtonText: String,
    onNegativeButtonClick: () -> Unit,
    isPositiveButtonEnabled: Boolean = true,
    isNegativeButtonVisible: Boolean = true,
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
            if (isNegativeButtonVisible) {
                TextButton(
                    onClick = onNegativeButtonClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = negativeButtonText.uppercase(),
                        fontWeight = FontWeight.Bold,
                        // TODO: style = MaterialTheme.typography.subtitle2
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
            Button(
                onClick = onPositiveButtonClick,
                enabled = isPositiveButtonEnabled,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = positiveButtonText.uppercase(),
                    fontWeight = FontWeight.Bold,
                    // TODO: style = MaterialTheme.typography.subtitle2
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
            positiveButtonText = "register",
            onPositiveButtonClick = {},
            negativeButtonText = "cancel",
            onNegativeButtonClick = {},
        )
    }
}