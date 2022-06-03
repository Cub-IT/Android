package com.example.cubit.navigation.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.cubit.navigation.flow.NavigationFlow
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_in.SingInScreen
import com.example.feature_user.presentation.sign_up.SignUpViewModel
import com.example.feature_user.presentation.sign_up.SingUpScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthNavigator2 @Inject constructor(
    private val navController: NavHostController,
    private val navigationFlow: NavigationFlow
) {

    fun navigateTo(authNavTarget: AuthNavTarget) {
        when (authNavTarget) {
            is AuthNavTarget.Back -> navController.navigateUp()
            is AuthNavTarget.Screen -> navController.navigate(authNavTarget.route)
        }
    }

    sealed class AuthNavTarget {
        object Back : AuthNavTarget()

        sealed class Screen(val route: String) : AuthNavTarget() {
            object SignIn : Screen(route = "signIn")

            object SignUp : Screen(route = "signUp")
        }
    }

    val builder: NavGraphBuilder.() -> Unit = {
        composable(route = AuthNavTarget.Screen.SignIn.route) {
            val vm = navigationFlow.getViewModel(modelClass = SignInViewModel::class.java)
            SingInScreen(viewModel = vm)
        }

        composable(route = AuthNavTarget.Screen.SignUp.route) {
            val vm = navigationFlow.getViewModel(modelClass = SignUpViewModel::class.java)
            SingUpScreen(viewModel = vm)
        }
    }
}