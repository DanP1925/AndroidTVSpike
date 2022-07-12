package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result

interface PhotoRepository {

    fun savePhoto(photo: Photo)

    suspend fun getPhotos(): Result<List<Photo>>

    suspend fun getPhotosWithQuery(query: String): Result<List<Photo>>

    suspend fun getPhoto(photoId: String): Result<Photo>

}