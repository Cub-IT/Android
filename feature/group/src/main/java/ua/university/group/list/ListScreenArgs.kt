package ua.university.group.list

data class ListScreenArgs(
    val onGroupClicked: (GroupId: String) -> Unit,
    val onUserAvatarClicked: () -> Unit,
    val onAddGroupClicked: () -> Unit,
    val onJoinGroupClicked: () -> Unit,
)
