package ua.university.navigation.screen

internal sealed class Group(route: String) : NavTarget.Screen(route = "group/$route") {
    internal object List : Group(route = "list")
    internal data class Selected(val groupId: String) : Group(route = groupId)
    internal object Add : Group(route = "add")
    internal object Join : Group(route = "join")
}