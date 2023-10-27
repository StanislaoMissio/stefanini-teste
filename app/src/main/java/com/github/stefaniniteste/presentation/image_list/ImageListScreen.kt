package com.github.stefaniniteste.presentation.image_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.stefaniniteste.presentation.components.ImageCard

@Composable
fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val dialogState = remember {
        mutableStateOf(false)
    }

    if (state.imageList.isNotEmpty()) {
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

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.Red
            )
        }
    }

    if (state.error.isNotEmpty()) {
        dialogState.value = true
    }

    if (dialogState.value) {
        AlertDialog(
            confirmButton = {
                Button(onClick = {
                    dialogState.value = false
                    viewModel.clearErrorState()
                    viewModel.reload()
                }) {
                    Text(text = "Tentar novamente")
                }
            },
            dismissButton = {
                Button(onClick = {
                    dialogState.value = false
                    viewModel.clearErrorState()
                }) {
                    Text(text = "Fechar")
                }
            },
            onDismissRequest = {
                dialogState.value = false
                viewModel.clearErrorState()
            },
            text = { Text(text = state.error) },
            title = { Text(text = "Ops, algo deu errado") },
            shape = RoundedCornerShape(20),
            tonalElevation = 10.dp,
            properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
        )
    }

}