package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Post
import ua.university.post.add.screen.AddPostScreen
import ua.university.post.add.screen.AddPostScreenArgs
import ua.university.post.add.screen.AddPostScreenNavs
import ua.university.post.selected.screen.SelectedScreen
import ua.university.post.selected.screen.SelectedScreenArgs
import ua.university.post.selected.screen.SelectedScreenNavs

internal fun NavGraphBuilder.postGraph(getFlow: () -> NavigationFlow) {
    composable(route = Post.Add.route) {
        val navs = getFlow().getNavDirection(AddPostScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = AddPostScreenArgs(navs)
        AddPostScreen(args)
    }

    composable(
        route = Post.Selected("{postId}").route,
        arguments = listOf(navArgument("postId") { type = NavType.StringType })
    ) { backStackEntry ->
        val postId = backStackEntry.arguments?.getString("postId") ?: throw IllegalArgumentException()
        val navs = getFlow().getNavDirection(SelectedScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = SelectedScreenArgs(postId, navs)
        SelectedScreen(args)
    }
}