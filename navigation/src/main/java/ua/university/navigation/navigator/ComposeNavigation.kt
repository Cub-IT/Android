package ua.university.navigation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.navigator.graph.groupGraph
import ua.university.navigation.navigator.graph.postGraph
import ua.university.navigation.navigator.graph.settingsGraph
import ua.university.navigation.screen.Auth
import ua.university.navigation.screen.NavTarget
import javax.inject.Inject

class ComposeNavigation @Inject constructor(
    private val auth: ua.university.navigation.navigator.graph.Auth
) : Navigator() {
    private lateinit var navController: NavHostController
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
        navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Auth.LogIn.route // it just to fill the gap. this dest is never called. in the future it will be a splash screen
        ) {
            with(auth) { authGraph { navigationFlow!! } }
            groupGraph { navigationFlow!! }
            postGraph { navigationFlow!! }
            settingsGraph { navigationFlow!! }
        }
    }
}