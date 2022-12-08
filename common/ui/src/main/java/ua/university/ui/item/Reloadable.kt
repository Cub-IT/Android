package ua.university.ui.item

data class Reloadable<out T : Any?>(val value: T, val status: Status) {

    sealed class Status {
        object Idle: Status()
        object Loading : Status()
        data class Error(val reason: UiText) : Status()
    }
}