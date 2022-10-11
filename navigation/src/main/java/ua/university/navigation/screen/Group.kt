package ua.university.navigation.screen

internal sealed class Group(route: String) : NavTarget.Screen(route = "group/$route") {
    internal object List : Group(route = "list")
    internal data class Selected(val groupId: String) : Group(route = groupId)
    internal data class Members(val groupId: String) : Group(route = "$groupId/members")
    internal object Add : Group(route = "add")
}