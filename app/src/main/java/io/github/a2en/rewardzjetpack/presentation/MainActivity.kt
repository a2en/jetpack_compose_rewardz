package io.github.a2en.rewardzjetpack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.a2en.rewardzjetpack.presentation.util.Screen
import io.github.a2en.rewardzjetpack.ui.screens.LoginScreen
import io.github.a2en.rewardzjetpack.ui.screens.RegisterScreen
import io.github.a2en.rewardzjetpack.ui.theme.RewardzjetpackTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RewardzjetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.RegisterScreen.route) {
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen ({
                                navController.navigate(Screen.RegisterScreen.route)
                            })
                        }
                        composable(Screen.RegisterScreen.route) {
                            RegisterScreen (navController)
                        }
                    }
                }
            }
        }
    }
}

