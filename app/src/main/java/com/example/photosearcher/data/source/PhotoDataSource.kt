package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result

interface PhotoDataSource {

    suspend fun getPhotos(): Result<List<Photo>>

    suspend fun getPhotosWithQuery(query:String): Result<List<Photo>>

    suspend fun getPhoto(photoId: String): Result<Photo>

}