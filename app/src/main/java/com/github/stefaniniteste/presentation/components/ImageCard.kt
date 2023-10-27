package com.github.stefaniniteste.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.VideoFrameDecoder

@Composable
fun ImageCard(
    link: String,
    testTag: String
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current).components {
        add(VideoFrameDecoder.Factory())
    }.build()
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .width(150.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        SubcomposeAsyncImage(
            model = link,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            imageLoader = imageLoader,
            loading = {
                Box {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        )
    }
}