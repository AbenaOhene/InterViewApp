package com.abeapps.myapplication.model

import com.abeapps.myapplication.AppConstants.Companion.apiKey
import com.abeapps.myapplication.AppConstants.Companion.imageType
import com.abeapps.myapplication.AppConstants.Companion.perPage
import com.abeapps.myapplication.model.network.ImageAPIService

class ImageRepository {

    private val service = ImageAPIService.instance
    fun getImagesOnlineAsync(pageNumber: Int) =
        service?.getImagesAPIAsync(
            apiKey,
            perPage,
            imageType,
            pageNumber
        )
}