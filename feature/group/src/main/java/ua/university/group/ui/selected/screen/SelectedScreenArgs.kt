package ua.university.group.ui.selected.screen

data class SelectedScreenArgs(
    val groupId: String,
    val navs: SelectedScreenNavs,
)

data class SelectedScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
    val onAddPostClicked: (groupId: String) -> Unit,
    val onEditPostClicked: (postId: String) -> Unit,
)