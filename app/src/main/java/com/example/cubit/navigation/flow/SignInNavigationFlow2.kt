/*
package com.example.cubit.navigation.flow

import com.example.cubit.navigation.navigator.AuthNavigator2
import javax.inject.Inject

class SignInNavigationFlow2 @Inject constructor(
    private val authNavigator: AuthNavigator2
) {
    private lateinit var exit: () -> Unit

    fun start(exit: String) {
        this.exit = exit
        signIn()
    }

    private fun signUp() {
        authNavigator.navigateTo(
            AuthNavigator2.AuthNavTarget.Screen.SignUp(
                onSignInClickedNavigation = "",
                onSignUpClickedNavigation = "",
                onSignInClickedLambda = this::signIn,
                onSignUpClickedLambda = this::signIn
            )
        )
    }

    private fun signIn() {
        authNavigator.navigateTo(
            AuthNavigator2.AuthNavTarget.SignIn(
                onSignInClicked = exit,
                onSignUpClicked = this::signUp
            )
        )
    }
}*/
