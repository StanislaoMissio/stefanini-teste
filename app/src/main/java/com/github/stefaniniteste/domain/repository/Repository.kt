package com.github.stefaniniteste.domain.repository

import com.github.stefaniniteste.commom.Response
import com.github.stefaniniteste.data.remote.Gallery

interface Repository {

    suspend fun getImageList(): Response<Gallery>

}