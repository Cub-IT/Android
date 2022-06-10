package com.example.cubit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.presentation.theme.CubITTheme
import com.example.feature_group.presentation.group_list.GroupListScreen
import com.example.feature_group.presentation.group_list.GroupListViewModel
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_in.SingInScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CubITTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    //Greeting("Android")
                    //SingInScreen()
                    GroupListScreen(viewModel = GroupListViewModel())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CubITTheme {
        Greeting("Android")
    }
}