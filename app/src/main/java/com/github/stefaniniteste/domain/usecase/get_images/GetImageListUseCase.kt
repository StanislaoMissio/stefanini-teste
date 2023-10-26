package com.github.stefaniniteste.domain.usecase.get_images

import com.github.stefaniniteste.commom.Resources
import com.github.stefaniniteste.data.remote.Gallery
import com.github.stefaniniteste.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Resources<List<Gallery>>> = flow {
        emit(Resources.Loading())
        val result = try {
            val images = repository.getImageList().data
            Resources.Success(images)
        } catch (exception: HttpException) {
            Resources.Error(
                message = exception.localizedMessage ?: "Tente novamente mais tarde!"
            )
        }
        emit(result)
    }

}