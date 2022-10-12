package ua.university.navigation.screen

internal sealed class Post(route: String) : NavTarget.Screen(route = "post/$route") {
    internal object Add : Post(route = "add")
}
