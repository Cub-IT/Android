package ua.university.navigation.navigator.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.navigation.screen.Auth

fun NavGraphBuilder.authGraph(navController: NavController) {
    composable(route = Auth.LogIn.route) {

    }

    composable(route = Auth.SignUp.route) {

    }
}