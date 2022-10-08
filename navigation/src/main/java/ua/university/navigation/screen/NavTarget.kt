package ua.university.navigation.screen

sealed class NavTarget {
    internal object Back : NavTarget()
    internal sealed class Screen(val route: String) : NavTarget()
}