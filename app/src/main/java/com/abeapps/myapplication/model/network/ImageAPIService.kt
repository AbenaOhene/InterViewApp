package com.abeapps.myapplication.model.network

import com.abeapps.myapplication.AppConstants.Companion.apiKeyQ
import com.abeapps.myapplication.AppConstants.Companion.baseURL
import com.abeapps.myapplication.AppConstants.Companion.imageAPI
import com.abeapps.myapplication.AppConstants.Companion.imageTypeQ
import com.abeapps.myapplication.AppConstants.Companion.pageQ
import com.abeapps.myapplication.AppConstants.Companion.perPageQ
import com.abeapps.myapplication.model.data.ImagesData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageAPIService {

    @GET(imageAPI)
    fun getImagesAPIAsync(
        @Query(apiKeyQ) key: String,
        @Query(perPageQ) perPage: Int,
        @Query(imageTypeQ) imageType: String,
        @Query(pageQ) page: Int
    ): Deferred<ImagesData>

    companion object{
        val instance: ImageAPIService? = Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ImageAPIService::class.java)
    }

}