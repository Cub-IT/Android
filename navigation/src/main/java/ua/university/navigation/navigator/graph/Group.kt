package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.group.list.ListScreen
import ua.university.group.list.ListScreenArgs
import ua.university.group.members.MembersScreen
import ua.university.group.members.MembersScreenArgs
import ua.university.group.selected.SelectedScreen
import ua.university.group.selected.SelectedScreenArgs
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Group

fun NavGraphBuilder.groupGraph(getFlow: () -> NavigationFlow) {
    composable(route = Group.List.route) {
        ListScreen(args = ListScreenArgs({}, {}, {}, {}))
    }

    composable(route = Group.Members("{groupId}").route) {
        MembersScreen(args = MembersScreenArgs({}, {}))
    }

    composable(route = Group.Selected("{groupId}").route) {
        SelectedScreen(args = SelectedScreenArgs({}, {}, "123456abc"))
    }
}