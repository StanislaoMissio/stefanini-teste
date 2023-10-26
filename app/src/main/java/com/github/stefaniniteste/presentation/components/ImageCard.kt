package com.github.stefaniniteste.presentation.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.github.stefaniniteste.data.remote.Image

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageCard(
    modifier: Modifier,
    image: Image
){
    Card(modifier = modifier) {
        GlideImage(
            model = image.link,
            contentDescription = null
        )
    }
}