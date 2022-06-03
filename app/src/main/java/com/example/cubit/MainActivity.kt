package com.example.cubit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.theme.CubITTheme
import com.example.core.util.exhaustive
import com.example.cubit.navigation.navigator.AuthNavigator
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_in.SingInScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var assistedFactory: SignInViewModel.Factory
    private val signInViewModel: SignInViewModel by viewModels {
        SignInViewModel.provideFactory(assistedFactory, {}, {})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CubITTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    SingInScreen(viewModel = signInViewModel)


                    val navController = rememberNavController()

                }
            }
        }
    }
}

@Composable
private fun NavigationComponent(
    navController: NavHostController,
    authNavigator: AuthNavigator
) {
    LaunchedEffect(Unit) {
        authNavigator.sharedFlow.onEach { navTarget ->
            when (navTarget) {
                is AuthNavigator.NavTarget.Back -> navController.navigateUp()
                is AuthNavigator.NavTarget.SignIn -> {
                    val t = navTarget
                    navController.navigate(navTarget.route, navigatorExtras = Navigator.Extras.)
                }
                is AuthNavigator.NavTarget.SignUp -> TODO()
            }.exhaustive
        }
    }

    NavHost(
        navController = navController,
        startDestination = AuthNavigator.NavTarget.SignIn().route
    ) {
        composable(
            route = "..."
        ) {
            SingInScreen(viewModel = SignInViewModel(
                onSignInClicked = {},
                onSignUpClicked = {},
                authRepository = authRepository
            ))
            ViewModelProvider.Fa
        }
        navigation(startDestination = "username", route = "login") {

        }
    }
}