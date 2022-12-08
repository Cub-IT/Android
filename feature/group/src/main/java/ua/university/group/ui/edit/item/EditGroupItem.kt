package ua.university.group.ui.edit.item

import ua.university.ui.item.InputFiled

data class EditGroupItem(
    val name: InputFiled,
    val description: InputFiled
)

fun previewNewGroupItem(): EditGroupItem {
    return EditGroupItem(
        name = InputFiled("Example name"),
        description = InputFiled("Example description"),
    )
}