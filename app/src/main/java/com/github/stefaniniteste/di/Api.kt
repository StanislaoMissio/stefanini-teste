package com.github.stefaniniteste.di

import com.github.stefaniniteste.commom.Response
import com.github.stefaniniteste.data.remote.Gallery
import com.github.stefaniniteste.data.remote.Image
import retrofit2.http.GET

interface Api {

    @GET("gallery/search/?q=cats")
    suspend fun getCatImages(): Response<Gallery>

}