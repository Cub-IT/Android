package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Post
import ua.university.post.ui.add.screen.AddPostScreen
import ua.university.post.ui.add.screen.AddPostScreenArgs
import ua.university.post.ui.add.screen.AddPostScreenNavs
import ua.university.post.ui.selected.screen.SelectedScreen
import ua.university.post.ui.selected.screen.SelectedScreenArgs
import ua.university.post.ui.selected.screen.SelectedScreenNavs

internal fun NavGraphBuilder.postGraph(getFlow: () -> NavigationFlow) {
    composable(route = Post.Add.route) {
        getFlow().getNavDirection(AddPostScreenNavs::class.java)?.let {
            val args = AddPostScreenArgs(it)
            AddPostScreen(args)
        }
    }

    composable(
        route = Post.Selected("{postId}").route,
        arguments = listOf(navArgument("postId") { type = NavType.StringType })
    ) { backStackEntry ->
        val postId = backStackEntry.arguments?.getString("postId") ?: throw IllegalArgumentException()
        getFlow().getNavDirection(SelectedScreenNavs::class.java)?.let {
            val args = SelectedScreenArgs(postId, it)
            SelectedScreen(args)
        }
    }
}