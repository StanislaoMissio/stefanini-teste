package com.github.stefaniniteste.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.stefaniniteste.presentation.image_list.ImageListScreen
import com.github.stefaniniteste.ui.theme.StefaniniTesteTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StefaniniTesteTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ImageListScreen.route
                    ) {
                        composable(
                            route = Screen.ImageListScreen.route
                        ) {
                            ImageListScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}