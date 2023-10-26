package com.github.stefaniniteste.presentation.image_list

import com.github.stefaniniteste.data.remote.Gallery

data class ImageListState(
    val isLoading: Boolean = false,
    val imageList: List<Gallery> = emptyList(),
    val error: String = ""
)