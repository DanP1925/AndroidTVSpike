package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result

interface PhotoDataSource {

    suspend fun getPhotos(): Result<List<Photo>>

}