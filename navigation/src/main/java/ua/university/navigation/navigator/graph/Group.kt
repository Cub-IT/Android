package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.university.group.ui.add.mvi.AddGroupViewModel
import ua.university.group.ui.add.screen.AddGroupScreen
import ua.university.group.ui.add.screen.AddGroupScreenArgs
import ua.university.group.ui.add.screen.AddGroupScreenNavs
import ua.university.group.ui.join.mvi.JoinGroupViewModel
import ua.university.group.ui.join.screen.JoinGroupScreen
import ua.university.group.ui.join.screen.JoinGroupScreenArgs
import ua.university.group.ui.join.screen.JoinGroupScreenNavs
import ua.university.group.ui.list.mvi.GroupListViewModel
import ua.university.group.ui.list.screen.ListScreen
import ua.university.group.ui.list.screen.ListScreenArgs
import ua.university.group.ui.list.screen.ListScreenNavs
import ua.university.group.ui.selected.mvi.GroupViewModel
import ua.university.group.ui.selected.screen.SelectedScreen
import ua.university.group.ui.selected.screen.SelectedScreenArgs
import ua.university.group.ui.selected.screen.SelectedScreenNavs
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Group
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Group @Inject constructor(
    private val groupListViewModelFactory: GroupListViewModel.Factory,
    private val groupViewModelFactory: GroupViewModel.Factory,
    private val addGroupViewModelFactory: AddGroupViewModel.Factory,
    private val joinGroupViewModelFactory: JoinGroupViewModel.Factory,
) {
    internal fun NavGraphBuilder.groupGraph(getFlow: () -> NavigationFlow) {
        composable(route = Group.List.route) {
            getFlow().getNavDirection(ListScreenNavs::class.java)?.let {
                val args = ListScreenArgs(it)
                ListScreen(args, groupListViewModelFactory)
            }
        }

        composable(
            route = Group.Selected("{groupId}").route,
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId") ?: throw IllegalArgumentException()
            getFlow().getNavDirection(SelectedScreenNavs::class.java)?.let {
                val args = SelectedScreenArgs(groupId, it)
                SelectedScreen(args, groupViewModelFactory)
            }
        }

        composable(route = Group.Add.route) {
            getFlow().getNavDirection(AddGroupScreenNavs::class.java)?.let {
                val args = AddGroupScreenArgs(it)
                AddGroupScreen(args, addGroupViewModelFactory)
            }
        }

        composable(route = Group.Join.route) {
            getFlow().getNavDirection(JoinGroupScreenNavs::class.java)?.let {
                val args = JoinGroupScreenArgs(it)
                JoinGroupScreen(args, joinGroupViewModelFactory)
            }
        }
    }
}