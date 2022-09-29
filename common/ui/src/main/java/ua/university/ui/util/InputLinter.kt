package ua.university.ui.util

typealias Condition = (input: String) -> Boolean

class InputLinter {

    private val rules: MutableList<Pair<Condition, Int>> = mutableListOf()

    fun addRule(errorMessage: Int, condition: Condition): InputLinter {
        rules.add(Pair(condition, errorMessage))
        return this
    }

    fun check(input: String): Int? {
        rules.forEach { rule: Pair<Condition, Int> ->
            if (!rule.first(input)) {
                return rule.second
            }
        }

        return null
    }

}