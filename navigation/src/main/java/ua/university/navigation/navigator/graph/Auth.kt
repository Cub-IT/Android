package ua.university.navigation.navigator.graph

import android.app.Activity
import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import ua.university.auth.ui.log_in.mvi.LogInViewModel
import ua.university.auth.ui.log_in.screen.LogInScreen
import ua.university.auth.ui.log_in.screen.LogInScreenArgs
import ua.university.auth.ui.log_in.screen.LogInScreenNavs
import ua.university.auth.ui.sign_up.mvi.SignUpViewModel
import ua.university.auth.ui.sign_up.screen.SignUpScreen
import ua.university.auth.ui.sign_up.screen.SignUpScreenArgs
import ua.university.auth.ui.sign_up.screen.SignUpScreenNavs
import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.Auth
import javax.inject.Inject

class Auth @Inject constructor(
    private val logInViewModelFactory: LogInViewModel.Factory,
    private val signUpViewModelFactory: SignUpViewModel.Factory
) {
    internal fun NavGraphBuilder.authGraph(getFlow: () -> NavigationFlow) {
        composable(route = Auth.LogIn.route) {
            val navs = getFlow().getNavDirection(LogInScreenNavs::class.java)
                ?: throw IllegalStateException()
            val args = LogInScreenArgs(navs)
            LogInScreen(args = args, factory = logInViewModelFactory)
        }

        composable(route = Auth.SignUp.route) {
            val navs = getFlow().getNavDirection(SignUpScreenNavs::class.java)
                ?: throw IllegalStateException()
            val args = SignUpScreenArgs(navs)
            SignUpScreen(args = args, factory = signUpViewModelFactory)
        }
    }
}