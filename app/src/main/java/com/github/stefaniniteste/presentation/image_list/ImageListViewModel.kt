package com.github.stefaniniteste.presentation.image_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stefaniniteste.commom.Resources
import com.github.stefaniniteste.domain.usecase.get_images.GetImageListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ImageListState())
    val state: State<ImageListState> = _state

    init {
        getImages()
    }

    private fun getImages() {
        getImageListUseCase().onEach { result ->
            when (result) {
                is Resources.Success -> _state.value =
                    ImageListState(imageList = result.data ?: emptyList())
                is Resources.Loading -> _state.value = ImageListState(isLoading = true)
                is Resources.Error -> _state.value = ImageListState(
                    error = result.message ?: "Ops algo, deu errado tente novamente mais tarde!"
                )
            }
        }.launchIn(viewModelScope)
    }

}