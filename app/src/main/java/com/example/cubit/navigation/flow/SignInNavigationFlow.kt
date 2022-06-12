/*
package com.example.cubit.navigation.flow

import com.example.cubit.navigation.navigator.AuthNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInNavigationFlow @Inject constructor(
    private val authNavigator: AuthNavigator
) {
    private lateinit var exit: () -> Unit

    fun start(exit: () -> Unit) {
        this.exit = exit
        signIn()
    }

    private fun signUp() {
        authNavigator.navigateTo(
            AuthNavigator.NavTarget.SignUp(
                onSignInClicked = this::signIn,
                onSignUpClicked = this::signIn
            )
        )
    }

    private fun signIn() {
        authNavigator.navigateTo(
            AuthNavigator.NavTarget.SignIn(
                onSignInClicked = exit,
                onSignUpClicked = this::signUp
            )
        )
    }
}*/
