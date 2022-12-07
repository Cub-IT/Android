package ua.university.navigation.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
import javax.inject.Singleton

@Singleton
class Auth @Inject constructor(
    private val logInViewModelFactory: LogInViewModel.Factory,
    private val signUpViewModelFactory: SignUpViewModel.Factory,
) {
    internal fun NavGraphBuilder.authGraph(getFlow: () -> NavigationFlow) {
        composable(route = Auth.LogIn.route) {
            getFlow().getNavDirection(LogInScreenNavs::class.java)?.let {
                val args = LogInScreenArgs(it)
                LogInScreen(args = args, factory = logInViewModelFactory)
            }
        }

        composable(route = Auth.SignUp.route) {
            getFlow().getNavDirection(SignUpScreenNavs::class.java)?.let {
                val args = SignUpScreenArgs(it)
                SignUpScreen(args = args, factory = signUpViewModelFactory)
            }
        }
    }
}