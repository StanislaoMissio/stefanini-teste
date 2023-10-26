package com.github.stefaniniteste.data.remote

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("type")
    val type: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("link")
    val link: String
)
