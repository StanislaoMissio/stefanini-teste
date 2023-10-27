package com.github.stefaniniteste.commom

import kotlinx.serialization.SerialName


data class Response<T>(
    @SerialName("data")
    val data: List<T>
)