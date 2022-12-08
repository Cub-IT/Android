package ua.university.navigation.screen

internal sealed class Post(route: String) : NavTarget.Screen(route = "post/$route") {
    internal data class Add(val groupId: String) : Post(route = "add/$groupId")
}
