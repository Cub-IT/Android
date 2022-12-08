package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Post
import ua.university.post.ui.add.mvi.AddPostViewModel
import ua.university.post.ui.add.screen.AddPostScreen
import ua.university.post.ui.add.screen.AddPostScreenArgs
import ua.university.post.ui.add.screen.AddPostScreenNavs
import ua.university.post.ui.selected.screen.SelectedScreen
import ua.university.post.ui.selected.screen.SelectedScreenArgs
import ua.university.post.ui.selected.screen.SelectedScreenNavs
import javax.inject.Inject

class Post @Inject constructor(
    private val addPostViewModelFactory: AddPostViewModel.Factory,
) {
    internal fun NavGraphBuilder.postGraph(getFlow: () -> NavigationFlow) {
        composable(
            route = Post.Add("{groupId}").route,
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId") ?: throw IllegalArgumentException()
            getFlow().getNavDirection(AddPostScreenNavs::class.java)?.let {
                val args = AddPostScreenArgs(groupId, it)
                AddPostScreen(args, addPostViewModelFactory)
            }
        }
    }
}