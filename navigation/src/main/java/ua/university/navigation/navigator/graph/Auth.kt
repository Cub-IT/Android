package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.auth.log_in.screen.LogInScreen
import ua.university.auth.log_in.screen.LogInScreenArgs
import ua.university.auth.log_in.screen.LogInScreenNavs
import ua.university.auth.sign_up.screen.SignUpScreen
import ua.university.auth.sign_up.screen.SignUpScreenArgs
import ua.university.auth.sign_up.screen.SignUpScreenNavs
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Auth

internal fun NavGraphBuilder.authGraph(getFlow: () -> NavigationFlow) {
    composable(route = Auth.LogIn.route) {
        val navs = getFlow().getNavDirection(LogInScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = LogInScreenArgs(navs)
        LogInScreen(args)
    }

    composable(route = Auth.SignUp.route) {
        val navs = getFlow().getNavDirection(SignUpScreenNavs::class.java)
            ?: throw IllegalStateException()
        val args = SignUpScreenArgs(navs)
        SignUpScreen(args)
    }
}