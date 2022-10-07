package ua.university.group.members

data class MembersScreenArgs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
)
