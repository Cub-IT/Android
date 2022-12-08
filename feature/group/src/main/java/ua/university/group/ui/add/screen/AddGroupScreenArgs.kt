package ua.university.group.ui.add.screen

data class AddGroupScreenArgs(
    val navs: AddGroupScreenNavs
)

data class AddGroupScreenNavs(
    val onBackClicked: () -> Unit,
    val onCreateGroupClicked: (groupId: String) -> Unit,
)