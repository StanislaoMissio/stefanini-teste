package com.github.stefaniniteste.data.remote

import kotlinx.serialization.SerialName

data class Image(
    @SerialName("type")
    val type: String,
    @SerialName("description")
    val description: String,
    @SerialName("link")
    val link: String
)
