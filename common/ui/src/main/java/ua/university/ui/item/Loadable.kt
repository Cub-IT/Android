package ua.university.ui.item

sealed class Loadable<out T : Any> {
    object Initial : Loadable<Nothing>()
    object Loading : Loadable<Nothing>()
    data class Success<out T : Any>(val value: T) : Loadable<T>()
    data class Error(val reason: String) : Loadable<Nothing>()
}