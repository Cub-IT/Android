package ua.university.group.selected

data class SelectedScreenArgs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,

    val groupId: String,
)