package ua.university.post.selected.screen

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.core.text.util.LinkifyCompat
import ua.university.post.selected.item.previewPostItem
import ua.university.post.selected.mvi.State
import ua.university.ui.item.Reloadable
import ua.university.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedScreen(args: SelectedScreenArgs) {
    val state = State(Reloadable(
        value = previewPostItem(),
        status = Reloadable.Status.Idle
    ))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { args.navs.onBackClicked() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = state.post.value.title,
                style = Typography.headlineMedium,
            )
            Divider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
            AndroidView(
                factory = { context ->
                    val textView = TextView(context)

                    textView.text = state.post.value.content
                    textView.textSize = 16F
                    textView.setTextColor("#000000".toColorInt())
                    LinkifyCompat.addLinks(textView, Linkify.ALL)
                    textView.movementMethod = LinkMovementMethod.getInstance()

                    textView
                }
            )
        }
    }
}