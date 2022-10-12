package ua.university.group.members.screen

data class MembersScreenArgs(
    val groupId: String,
    val navs: MembersScreenNavs,
)

data class MembersScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
)