package com.github.stefaniniteste.presentation

sealed class Screen(val route: String) {

    object ImageListScreen: Screen("image_list_screen")

}