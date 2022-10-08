package ua.university.group.list

data class ListScreenArgs(
    val navs: ListScreenNavs,
)

data class ListScreenNavs(
    val onGroupClicked: (groupId: String) -> Unit,
    val onUserAvatarClicked: () -> Unit,
    val onAddGroupClicked: () -> Unit,
    val onJoinGroupClicked: () -> Unit,
)