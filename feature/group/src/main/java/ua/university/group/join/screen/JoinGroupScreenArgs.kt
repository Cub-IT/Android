package ua.university.group.join.screen

data class JoinGroupScreenArgs(
    val navs: JoinGroupScreenNavs
)

data class JoinGroupScreenNavs(
    val onBackClicked: () -> Unit,
    val onJoinGroupClicked: (groupId: String) -> Unit,
)