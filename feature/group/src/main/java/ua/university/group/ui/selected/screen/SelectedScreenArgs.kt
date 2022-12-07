package ua.university.group.ui.selected.screen

data class SelectedScreenArgs(
    val groupId: String,
    val navs: SelectedScreenNavs,
)

data class SelectedScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
    //val onPostClicked: (postId: String) -> Unit,
    val onAddPostClicked: () -> Unit,
    val onEditPostClicked: (postId: String) -> Unit,
)