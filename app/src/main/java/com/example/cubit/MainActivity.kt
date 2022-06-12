package com.example.cubit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.theme.CubITTheme
import com.example.core.util.ViewModelCreator
//import com.example.core.util.ViewModelFactory
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_in.SingInScreen
import com.example.feature_user.presentation.sign_up.SingUpScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /*inline fun <reified VM : ViewModel> ComponentActivity.viewModelCreator(
        noinline creator: ViewModelCreator<VM>
    ): Lazy<VM> {
        return viewModels { ViewModelFactory(creator) }
    }*/

    /*@Inject lateinit var factory: SignInViewModel.Factory
    val viewModel by viewModelCreator {
        factory.create(
            onSignInClicked = {},
            onSignUpClicked = {}
        )
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CubITTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    //SingInScreen(viewModel = viewModel)
                    //SingUpScreen()


                    val navController = rememberNavController()
                    //Greeting("Android")
                    //SingInScreen()
                    //GroupListScreen(viewModel = GroupListViewModel())
                }
            }
        }
    }
}

/*
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
}*/
