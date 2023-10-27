package com.github.stefaniniteste.presentation.image_list

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.stefaniniteste.data.remote.Gallery
import com.github.stefaniniteste.di.Module
import com.github.stefaniniteste.presentation.MainActivity
import com.github.stefaniniteste.presentation.Screen
import com.github.stefaniniteste.ui.theme.StefaniniTesteTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(Module::class)
class ImageListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            StefaniniTesteTheme {
                NavHost(navController = navController, startDestination = Screen.ImageListScreen.route) {
                    composable(route = Screen.ImageListScreen.route) {
                        ImageListScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun checkIfFirstImageIsLoading() {
        composeRule.onNodeWithTag("grid").assertIsDisplayed()
    }

}