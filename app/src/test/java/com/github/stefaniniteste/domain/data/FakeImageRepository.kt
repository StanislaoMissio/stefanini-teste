package com.github.stefaniniteste.domain.data

import com.github.stefaniniteste.commom.Response
import com.github.stefaniniteste.data.remote.Gallery
import com.github.stefaniniteste.di.Api
import com.github.stefaniniteste.domain.repository.Repository

class FakeImageRepository(private val api: Api): Repository {

    override suspend fun getImageList(): Response<Gallery> = api.getCatImages()

}