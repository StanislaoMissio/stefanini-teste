package com.github.stefaniniteste.domain.repository

import com.github.stefaniniteste.commom.Resources
import com.github.stefaniniteste.commom.Response
import com.github.stefaniniteste.data.remote.Image

interface Repository {

    suspend fun getImageList(): Resources<Response<Image>>

}