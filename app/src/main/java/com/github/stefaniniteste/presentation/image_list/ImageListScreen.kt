package com.github.stefaniniteste.presentation.image_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.stefaniniteste.presentation.components.ImageCard

@Composable
fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4)
        ) {
            items(key = { gallery ->
                gallery.id
            }, items = state.imageList) { gallery ->
                var link = ""
                link = if (gallery.link.startsWith("https://i.")) gallery.link
                else gallery.image!![0].link
                ImageCard(
                    link = link
                )
            }
        }
    }

}