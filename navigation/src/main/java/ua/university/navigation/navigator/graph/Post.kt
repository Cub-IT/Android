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
import ua.university.post.ui.edit.mvi.EditPostViewModel
import ua.university.post.ui.edit.screen.EditPostScreen
import ua.university.post.ui.edit.screen.EditPostScreenArgs
import ua.university.post.ui.edit.screen.EditPostScreenNavs
import javax.inject.Inject

class Post @Inject constructor(
    private val addPostViewModelFactory: AddPostViewModel.Factory,
    private val editPostViewModelFactory: EditPostViewModel.Factory,
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

        composable(
            route = Post.Edit("{groupId}", "{postId}").route,
            arguments = listOf(
                navArgument("groupId") { type = NavType.StringType },
                navArgument("postId") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId") ?: throw IllegalArgumentException()
            val postId = backStackEntry.arguments?.getString("postId") ?: throw IllegalArgumentException()
            getFlow().getNavDirection(EditPostScreenNavs::class.java)?.let {
                val args = EditPostScreenArgs(groupId, postId, it)
                EditPostScreen(args, editPostViewModelFactory)
            }
        }
    }
}