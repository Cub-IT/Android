package ua.university.group.ui.selected.screen

data class SelectedScreenArgs(
    val groupId: String,
    val navs: SelectedScreenNavs,
)

data class SelectedScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
    val onAddPostClicked: (groupId: String) -> Unit,
    val onEditPostClicked: (groupId: String, postId: String) -> Unit,
    val onEditGroupClicked: (groupId: String) -> Unit,
    val onDeleteGroupClicked: () -> Unit,
)