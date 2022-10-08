package ua.university.navigation.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.navigator.graph.authGraph
import ua.university.navigation.navigator.graph.groupGraph
import ua.university.navigation.screen.Auth
import ua.university.navigation.screen.NavTarget

internal class ComposeNavigation (private val navController: NavHostController) : Navigator {
    private var navigationFlow: NavigationFlow? = null

    override fun navigateTo(navTarget: NavTarget, navigationFlow: NavigationFlow) {
        this.navigationFlow = navigationFlow

        when (navTarget) {
            is NavTarget.Back -> navController.navigateUp()
            is NavTarget.Screen -> navController.navigate(navTarget.route)
        }
    }

    @Composable
    fun SetupNavGraph() {
        NavHost(
            navController = navController,
            startDestination = Auth.LogIn.route // it just to fill the gap. this dest is never called
        ) {
            authGraph { navigationFlow!! }
            groupGraph { navigationFlow!! }
        }
    }
}