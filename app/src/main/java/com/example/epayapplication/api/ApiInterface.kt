package com.example.epayapplication.api

import com.example.epayapplication.models.images.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        const val BASE_URL = "https://api.flickr.com/"
        const val API_KEY = "6f933ebd66da4a23419c4c8b2df387d4"
    }


    @GET("services/rest/?method=flickr.photos.search&api_key=$API_KEY")
    suspend fun searchPhotos(
        @Query("text") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): FlickrResponse
}