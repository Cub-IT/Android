package ua.university.ui.util

import androidx.annotation.StringRes
import ua.university.ui.item.UiText

typealias Condition = (input: String) -> Boolean

class InputLinter {

    private val rules: MutableList<Pair<Condition, Int>> = mutableListOf()

    fun addRule(@StringRes errorMessage: Int, condition: Condition): InputLinter {
        rules.add(Pair(condition, errorMessage))
        return this
    }

    fun check(input: String): UiText? {
        rules.forEach { rule: Pair<Condition, Int> ->
            if (!rule.first(input)) {
                return UiText.StringResource(rule.second)
            }
        }

        return null
    }

}