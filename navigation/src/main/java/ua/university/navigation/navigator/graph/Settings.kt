package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Settings
import ua.university.settings.profile.screen.ProfileScreen
import ua.university.settings.profile.screen.ProfileScreenArgs
import ua.university.settings.profile.screen.ProfileScreenNavs

internal fun NavGraphBuilder.settingsGraph(getFlow: () -> NavigationFlow) {
    composable(route = Settings.Profile.route) {
        getFlow().getNavDirection(ProfileScreenNavs::class.java)?.let {
            val args = ProfileScreenArgs(it)
            ProfileScreen(args)
        }
    }
}