package ua.university.group.selected.screen

data class SelectedScreenArgs(
    val groupId: String,
    val navs: SelectedScreenNavs,
)

data class SelectedScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
)