package com.abeapps.myapplication

class AppConstants {
    //https://pixabay.com/api/?key=22209845-0e41a1e637ade5054d52c799e&image_type=photo&page=1&per_page=50
    companion object{
        const val baseURL = "https://pixabay.com/"
        const val imageAPI = "/api/"
        const val apiKeyQ = "key"
        const val apiKey = "22209845-0e41a1e637ade5054d52c799e"
        const val imageTypeQ = "image_type"
        const val pageQ = "page"
        const val perPageQ = "per_page"

        const val perPage = 5
        const val imageType = "photo"
    }
}