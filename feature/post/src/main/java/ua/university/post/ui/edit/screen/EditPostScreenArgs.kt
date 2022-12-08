package ua.university.post.ui.edit.screen

data class EditPostScreenArgs(
    val groupId: String,
    val postId: String,
    val navs: EditPostScreenNavs,
)

data class EditPostScreenNavs(
    val onBackClicked: () -> Unit,
    val onCreatePostClicked: (postId: String) -> Unit,
)