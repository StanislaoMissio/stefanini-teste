package com.github.stefaniniteste.presentation.image_list

import com.github.stefaniniteste.data.remote.Image

data class ImageListState(
    val isLoading: Boolean = false,
    val imageList: List<Image> = emptyList(),
    val error: String = ""
)