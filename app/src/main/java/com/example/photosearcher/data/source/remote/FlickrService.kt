package com.example.photosearcher.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET("services/rest/")
    suspend fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") ak: String,
        @Query("tags") tags: String,
        @Query("text") text: String?,
        @Query("format") format:String?,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Response<GetPhotosResponse>

}