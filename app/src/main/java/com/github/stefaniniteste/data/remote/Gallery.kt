package com.github.stefaniniteste.data.remote

import kotlinx.serialization.SerialName


data class Gallery(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("images")
    val image: List<Image>?,
    @SerialName("link")
    val link: String
)