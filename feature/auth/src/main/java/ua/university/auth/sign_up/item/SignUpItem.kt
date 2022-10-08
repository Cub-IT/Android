package ua.university.auth.sign_up.item

import ua.university.ui.item.InputFiled

data class SignUpItem(
    val firstName: InputFiled,
    val lastName: InputFiled,
    val email: InputFiled,
    val password: InputFiled,
)