package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Settings
import ua.university.settings.profile.ui.mvi.UserViewModel
import ua.university.settings.profile.ui.screen.ProfileScreen
import ua.university.settings.profile.ui.screen.ProfileScreenArgs
import ua.university.settings.profile.ui.screen.ProfileScreenNavs
import javax.inject.Inject

class Settings @Inject constructor(
    private val profileViewModelFactory: UserViewModel.Factory
) {
    internal fun NavGraphBuilder.settingsGraph(getFlow: () -> NavigationFlow) {
        composable(route = Settings.Profile.route) {
            getFlow().getNavDirection(ProfileScreenNavs::class.java)?.let {
                val args = ProfileScreenArgs(it)
                ProfileScreen(args, profileViewModelFactory)
            }
        }
    }
}