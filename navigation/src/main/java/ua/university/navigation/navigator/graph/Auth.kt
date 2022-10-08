package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.auth.log_in.LogInScreen
import ua.university.auth.log_in.LogInScreenArgs
import ua.university.auth.sign_up.SignUpScreen
import ua.university.auth.sign_up.SignUpScreenArgs
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Auth

fun NavGraphBuilder.authGraph(getFlow: () -> NavigationFlow) {
    composable(route = Auth.LogIn.route) {
        LogInScreen(args = LogInScreenArgs({}, {}))
    }

    composable(route = Auth.SignUp.route) {
        SignUpScreen(args = SignUpScreenArgs({}, {}))
    }
}