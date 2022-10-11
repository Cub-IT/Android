package ua.university.group.join.mvi

import ua.university.ui.item.InputFiled
import ua.university.ui.item.Reloadable

data class State(
    val groupCode: Reloadable<InputFiled>
) {
    val isJoiningEnabled = false
}
