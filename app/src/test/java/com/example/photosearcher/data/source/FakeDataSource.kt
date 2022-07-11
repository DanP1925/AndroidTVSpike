package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result

class FakeDataSource(var photos: MutableList<Photo>? = mutableListOf()): PhotoDataSource {

    override suspend fun getPhotos(): Result<List<Photo>> {
        photos?.let { return Result.Success(ArrayList(it)) }
        return Result.Error(Exception("Photo not found"))
    }

}