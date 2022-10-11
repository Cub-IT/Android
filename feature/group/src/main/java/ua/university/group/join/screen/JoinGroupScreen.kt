package ua.university.group.join.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ua.university.group.R
import ua.university.group.join.mvi.State
import ua.university.ui.composable.CubitErrorMessage
import ua.university.ui.composable.CubitTextField
import ua.university.ui.item.InputFiled
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinGroupScreen(args: JoinGroupScreenArgs) {
    val uiState = State(Reloadable(
        value = InputFiled("ARkU4eRORvc4E"),
        status = Reloadable.Status.Loading
    ))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Join group")
                },
                navigationIcon = {
                    IconButton(onClick = { args.navs.onBackClicked() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    FilledTonalButton(
                        onClick = { args.navs.onJoinGroupClicked },
                        enabled = uiState.isJoiningEnabled
                    ) {
                        Text(text = "Join")
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(text = stringResource(R.string.join_group_code_description))
                CubitTextField(
                    field = uiState.groupCode.value,
                    onValueChange = { newValue -> },
                    modifier = Modifier.fillMaxWidth(),
                    label = UiText.StringResource(R.string.group_code),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
                )
            }

            if (uiState.groupCode.status is Reloadable.Status.Error) {
                CubitErrorMessage(
                    errorCause = (uiState.groupCode.status as Reloadable.Status.Error).reason,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (uiState.groupCode.status is Reloadable.Status.Loading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.75F)
                ) { }
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}