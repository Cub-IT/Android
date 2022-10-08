package ua.university.navigation.flow

import ua.university.group.list.ListScreenNavs
import ua.university.group.members.MembersScreenNavs
import ua.university.group.selected.SelectedScreenNavs
import ua.university.navigation.navigator.Navigator
import ua.university.navigation.screen.Group
import ua.university.navigation.screen.NavTarget

internal class GroupFlow(
    private val navigator: Navigator,
    private val onExit: () -> Unit,
) : NavigationFlow {
    override fun start() {
        navigator.navigateTo(navTarget = Group.List, navigationFlow = this)
    }

    override fun <T> getNavDirection(screenNavsClass: Class<T>): T? {
        return when (screenNavsClass.name) {
            ListScreenNavs::class.java.name -> onListScreen()
            MembersScreenNavs::class.java.name -> onMembersScreen()
            SelectedScreenNavs::class.java.name -> onSelectedScreen()

            else -> null
        } as? T
    }

    private fun onListScreen(): ListScreenNavs {
        return ListScreenNavs(
            onGroupClicked = { groupId ->
                navigator.navigateTo(navTarget = Group.Selected(groupId = groupId), navigationFlow = this)
            },
            onUserAvatarClicked = { /* TODO: implement screen */ },
            onAddGroupClicked = { /* TODO: implement screen */ },
            onJoinGroupClicked = { /* TODO: implement screen */ },
        )
    }

    private fun onMembersScreen(): MembersScreenNavs {
        return MembersScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onUserAvatarClicked = { /* TODO: implement screen */ },
        )
    }

    private fun onSelectedScreen(): SelectedScreenNavs {
        return SelectedScreenNavs(
            onBackClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onUserAvatarClicked = { /* TODO: implement screen */ },
        )
    }
}