package com.github.stefaniniteste.data.remote

import com.google.gson.annotations.SerializedName

data class Gallery(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("images")
    val image: List<Image>?,
    @SerializedName("link")
    val link: String
)