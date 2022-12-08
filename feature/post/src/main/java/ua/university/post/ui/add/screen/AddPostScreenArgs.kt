package ua.university.post.ui.add.screen

data class AddPostScreenArgs(
    val navs: AddPostScreenNavs
)

data class AddPostScreenNavs(
    val onBackClicked: () -> Unit,
    val onCreatePostClicked: (postId: String) -> Unit,
)