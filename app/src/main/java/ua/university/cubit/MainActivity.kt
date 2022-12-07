package ua.university.cubit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ua.university.navigation.RootNavigationFlow
import ua.university.navigation.navigator.ComposeNavigation
import ua.university.preferences.UserSharedPreferences
import ua.university.ui.theme.CubITTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: ComposeNavigation
    @Inject
    lateinit var userSharedPreferences: UserSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CubITTheme {
                navigator.SetupNavGraph()

                val rootNavigationFlow = RootNavigationFlow(navigator, userSharedPreferences)
                rootNavigationFlow.start()
            }
        }
    }
}