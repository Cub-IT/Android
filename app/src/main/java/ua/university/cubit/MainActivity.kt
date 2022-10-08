package ua.university.cubit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ua.university.navigation.RootNavigationFlow
import ua.university.navigation.navigator.ComposeNavigation
import ua.university.ui.theme.CubITTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CubITTheme {
                val navController = rememberNavController()
                val navigator = ComposeNavigation(navController)
                navigator.SetupNavGraph()

                val rootNavigationFlow = RootNavigationFlow(navigator)
                rootNavigationFlow.start()
            }
        }
    }
}