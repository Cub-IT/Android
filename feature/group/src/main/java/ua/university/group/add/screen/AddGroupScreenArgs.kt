package ua.university.group.add.screen

data class AddGroupScreenArgs(
    val navs: AddGroupScreenNavs
)

data class AddGroupScreenNavs(
    val onBackClicked: () -> Unit,
    val onUserAvatarClicked: () -> Unit,
    val onCreateGroup: (groupId: String) -> Unit,
)