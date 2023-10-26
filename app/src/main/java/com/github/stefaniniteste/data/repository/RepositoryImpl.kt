package com.github.stefaniniteste.data.repository

import com.github.stefaniniteste.di.Api
import com.github.stefaniniteste.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api): Repository {

    override suspend fun getImageList() = api.getCatImages()

}