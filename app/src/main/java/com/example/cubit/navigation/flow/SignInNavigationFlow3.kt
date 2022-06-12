package com.example.cubit.navigation.flow

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.data.repository.AuthRepository
import com.example.cubit.navigation.navigator.AuthNavigator2
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_up.SignUpViewModel
import javax.inject.Inject

class SignInNavigationFlow3 @Inject private constructor(
    private val exit: () -> Unit,
    private val authNavigator: AuthNavigator2,
    private val authRepository: AuthRepository
) : NavigationFlow {

    fun start() {
        authNavigator.navigateTo(
            AuthNavigator2.AuthNavTarget.Screen.SignIn
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> getViewModel(modelClass: Class<T>): T {
        return when (modelClass) {
            SignInViewModel::class.java -> {
                // here goes an implementation idea (only the idea how viewModel looks like!)
                SignInViewModel(
                    onSignInClicked = exit,
                    onSignUpClicked = {
                        authNavigator.navigateTo(
                            AuthNavigator2.AuthNavTarget.Screen.SignUp
                        )
                    },
                    authRepository = authRepository
                )
            }
            SignUpViewModel::class.java -> {
                // here goes an implementation idea (only the idea how viewModel looks like!)
                SignUpViewModel(
                    onSignInClicked = {
                        authNavigator.navigateTo(AuthNavigator2.AuthNavTarget.Back)
                    },
                    onSignUpClicked = {
                        authNavigator.navigateTo(AuthNavigator2.AuthNavTarget.Back)
                    },
                    authRepository = authRepository
                )
            }
            else -> {
                throw IllegalArgumentException("No ViewModel registered for $modelClass")
            }
        } as T
    }

}