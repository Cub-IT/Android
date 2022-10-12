package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Post
import ua.university.post.add.screen.AddPostScreen
import ua.university.post.add.screen.AddPostScreenArgs
import ua.university.post.add.screen.AddPostScreenNavs

internal fun NavGraphBuilder.postGraph(getFlow: () -> NavigationFlow) {
    composable(route = Post.Add.route) {
        val navs = getFlow().getNavDirection(AddPostScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = AddPostScreenArgs(navs)
        AddPostScreen(args)
    }
}