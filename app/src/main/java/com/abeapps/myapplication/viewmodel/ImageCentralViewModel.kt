package com.abeapps.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abeapps.myapplication.model.ImageRepository
import com.abeapps.myapplication.model.data.ImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageCentralViewModel: ViewModel() {

    private val repository = ImageRepository()
    private var pageNumber = 1

    private val _imageData = MutableLiveData<ImageResponse>()
    val imageData:LiveData<ImageResponse>
        get() = _imageData

    init {
        getImages()
    }

    fun getImages() {
        try{
            viewModelScope.launch(Dispatchers.IO) {
                val images = repository.getImagesOnlineAsync(pageNumber)?.await()
                images?.hits?.let {
                    _imageData.postValue(ImageResponse.Success(it))
                    pageNumber += 1
                } ?: _imageData.postValue(ImageResponse.Error("data was empty"))

            }

        } catch (e: Exception){
            _imageData.postValue(ImageResponse.Error(e.localizedMessage?:"Error Occurred"))
        }
    }

}