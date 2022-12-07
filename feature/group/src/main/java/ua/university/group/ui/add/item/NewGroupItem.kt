package ua.university.group.ui.add.item

import ua.university.ui.item.InputFiled

data class NewGroupItem(
    val name: InputFiled,
    val description: InputFiled
)

fun previewNewGroupItem(): NewGroupItem {
    return NewGroupItem(
        name = InputFiled("Example name"),
        description = InputFiled("Example description"),
    )
}