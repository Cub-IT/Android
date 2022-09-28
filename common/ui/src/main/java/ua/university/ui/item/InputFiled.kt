package ua.university.ui.item

import androidx.annotation.StringRes

data class InputFiled(
    val value: String,
    @StringRes val error: Int? = null,
)