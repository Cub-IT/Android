/*
package com.example.cubit.navigation.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_in.SingInScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthNavigator @Inject constructor() {
    private val _sharedFlow = MutableSharedFlow<NavTarget>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavTarget) {
        _sharedFlow.tryEmit(navTarget)
    }

    sealed class NavTarget(val route: String = "") {
        object Back : NavTarget()

        data class SignIn(
            val onSignInClicked: () -> Unit,
            val onSignUpClicked: () -> Unit
        ) : NavTarget()

        data class SignUp(
            val onSignInClicked: () -> Unit,
            val onSignUpClicked: () -> Unit
        ) : NavTarget()
    }

    val builder: NavGraphBuilder.() -> Unit = {
        composable(route = "signIn") {
            SingInScreen(viewModel = SignInViewModel(
                onSignInClicked = (_sharedFlow.last() as NavTarget.SignIn).onSignInClicked,
                onSignUpClicked = {},
                authRepository = authRepository
            ))
        }
    }

}*/
