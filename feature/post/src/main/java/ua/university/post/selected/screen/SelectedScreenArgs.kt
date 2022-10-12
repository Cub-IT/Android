package ua.university.post.selected.screen

data class SelectedScreenArgs(
    val postId: String,
    val navs: SelectedScreenNavs,
)

data class SelectedScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
)