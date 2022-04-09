package com.example.feature_user.enter.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.feature_user.R

@Composable
fun EnterScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextButton(onClick = { /*TODO: navigate to SignInScreen*/ }) {
            Text(text = stringResource(R.string.sign_in))
        }
        Spacer(modifier = Modifier.padding(16.dp))
        TextButton(onClick = { /*TODO: navigate to SignUpScreen*/ }) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}