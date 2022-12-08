package ua.university.group.ui.edit.screen

data class EditGroupScreenArgs(
    val groupId: String,
    val navs: EditGroupScreenNavs
)

data class EditGroupScreenNavs(
    val onBackClicked: () -> Unit,
    val onCreateGroupClicked: (groupId: String) -> Unit,
)