package com.github.stefaniniteste.commom

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("data")
    val data: List<T>
)