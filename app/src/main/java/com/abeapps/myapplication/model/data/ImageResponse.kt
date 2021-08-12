package com.abeapps.myapplication.model.data

sealed class ImageResponse{
    data class Success(val data: List<Hit>): ImageResponse()
    data class Error(val error: String): ImageResponse()
    data class Loading(val isLoading: Boolean) : ImageResponse()
}
