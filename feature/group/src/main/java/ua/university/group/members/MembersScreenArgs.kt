package ua.university.group.members

data class MembersScreenArgs(
    val groupId: String,
    val navs: MembersScreenNavs,
)

data class MembersScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
)