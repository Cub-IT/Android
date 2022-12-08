package ua.university.group.ui.list.screen.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.university.group.R
import ua.university.group.ui.list.item.GroupItem
import ua.university.ui.theme.Typography

@Composable
internal fun GroupList(
    groups: List<GroupItem>,
    modifier: Modifier = Modifier,
    onGroupClick: (groupId: String) -> Unit,
) {
    if (groups.isEmpty()) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = stringResource(R.string.no_groups),
            style = Typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(groups) { group ->
            GroupCard(
                modifier = Modifier.padding(vertical = 4.dp),
                group = group,
                onClick = { onGroupClick(group.id) }
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GroupListPreview() {
    GroupList(groups = previewGroupItemList(), onGroupClick = {})
}*/
