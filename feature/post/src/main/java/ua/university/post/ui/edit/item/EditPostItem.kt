package ua.university.post.ui.edit.item

import ua.university.ui.item.InputFiled


data class EditPostItem(
    val title: InputFiled,
    val content: InputFiled // description
)

fun previewEditPostItem(): EditPostItem {
    return EditPostItem(
        InputFiled("Example title text"),
        InputFiled("Some random text for a new post!")
    )
}