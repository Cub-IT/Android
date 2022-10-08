package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.auth.log_in.LogInScreen
import ua.university.auth.log_in.LogInScreenArgs
import ua.university.auth.log_in.LogInScreenNavs
import ua.university.auth.sign_up.SignUpScreen
import ua.university.auth.sign_up.SignUpScreenArgs
import ua.university.auth.sign_up.SignUpScreenNavs
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