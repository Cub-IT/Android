package ua.university.post.add.item

import ua.university.ui.item.InputFiled

data class NewPostItem(
    val title: InputFiled,
    val content: InputFiled // description
)

fun previewNewPostItem(): NewPostItem {
    return NewPostItem(
        InputFiled("Example title text"),
        InputFiled("Some random text for a new post!")
    )
}