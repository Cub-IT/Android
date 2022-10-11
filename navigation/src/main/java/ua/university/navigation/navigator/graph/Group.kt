package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.university.group.add.screen.AddGroupScreen
import ua.university.group.add.screen.AddGroupScreenArgs
import ua.university.group.add.screen.AddGroupScreenNavs
import ua.university.group.join.screen.JoinGroupScreen
import ua.university.group.join.screen.JoinGroupScreenArgs
import ua.university.group.join.screen.JoinGroupScreenNavs
import ua.university.group.list.screen.ListScreen
import ua.university.group.list.screen.ListScreenArgs
import ua.university.group.list.screen.ListScreenNavs
import ua.university.group.members.MembersScreen
import ua.university.group.members.MembersScreenArgs
import ua.university.group.members.MembersScreenNavs
import ua.university.group.selected.screen.SelectedScreen
import ua.university.group.selected.screen.SelectedScreenArgs
import ua.university.group.selected.screen.SelectedScreenNavs
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Group

internal fun NavGraphBuilder.groupGraph(getFlow: () -> NavigationFlow) {
    composable(route = Group.List.route) {
        val navs = getFlow().getNavDirection(ListScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = ListScreenArgs(navs)
        ListScreen(args)
    }

    composable(
        route = Group.Selected("{groupId}").route,
        arguments = listOf(navArgument("groupId") { type = NavType.StringType })
    ) {backStackEntry ->
        val groupId = backStackEntry.arguments?.getString("groupId") ?: throw IllegalArgumentException()
        val navs = getFlow().getNavDirection(SelectedScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = SelectedScreenArgs(groupId, navs)
        SelectedScreen(args)
    }

    composable(
        route = Group.Members("{groupId}").route,
        arguments = listOf(navArgument("groupId") { type = NavType.StringType })
    ) { backStackEntry ->
        val groupId = backStackEntry.arguments?.getString("groupId") ?: throw IllegalArgumentException()
        val navs = getFlow().getNavDirection(MembersScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = MembersScreenArgs(groupId, navs)
        MembersScreen(args)
    }

    composable(route = Group.Add.route) {
        val navs = getFlow().getNavDirection(AddGroupScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = AddGroupScreenArgs(navs)
        AddGroupScreen(args)
    }

    composable(route = Group.Join.route) {
        val navs = getFlow().getNavDirection(JoinGroupScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = JoinGroupScreenArgs(navs)
        JoinGroupScreen(args)
    }
}