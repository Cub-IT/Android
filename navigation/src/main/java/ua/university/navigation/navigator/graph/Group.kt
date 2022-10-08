package ua.university.navigation.navigator.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.university.navigation.screen.Group

fun NavGraphBuilder.groupGraph(navController: NavController) {
    composable(route = Group.List.route) {

    }

    composable(route = Group.Members("{groupId}").route) {

    }

    composable(route = Group.Selected("{groupId}").route) {

    }
}