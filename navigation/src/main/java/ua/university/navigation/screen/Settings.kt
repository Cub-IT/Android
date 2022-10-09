package ua.university.navigation.screen

internal sealed class Settings(route: String) : NavTarget.Screen(route = "settings/$route") {
    internal object Profile : Settings(route = "profile")
}
