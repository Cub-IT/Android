package ua.university.navigation.flow

import ua.university.auth.ui.log_in.screen.LogInScreenNavs
import ua.university.auth.ui.sign_up.screen.SignUpScreenNavs
import ua.university.navigation.navigator.Navigator
import ua.university.navigation.screen.Auth
import ua.university.navigation.screen.NavTarget

internal class AuthFlow(
    private val navigator: Navigator,
    private val onExit: () -> Unit,
) : NavigationFlow {
    override fun start() {
        navigator.navigateTo(navTarget = Auth.LogIn, navigationFlow = this)
    }

    override fun <T> getNavDirection(screenNavsClass: Class<T>): T? {
        return when (screenNavsClass.name) {
            LogInScreenNavs::class.java.name -> onLogInScreen()
            SignUpScreenNavs::class.java.name -> onSignUpScreen()

            else -> null
        } as? T
    }

    private fun onLogInScreen(): LogInScreenNavs {
        return LogInScreenNavs(
            onLogInClicked = onExit,
            onSignUpClicked = { navigator.navigateTo(navTarget = Auth.SignUp, navigationFlow = this) },
        )
    }

    private fun onSignUpScreen(): SignUpScreenNavs {
        return SignUpScreenNavs(
            onLogInClicked = { navigator.navigateTo(navTarget = NavTarget.Back, navigationFlow = this) },
            onSignUpClicked = onExit,
        )
    }
}