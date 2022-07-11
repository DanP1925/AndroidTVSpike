package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result
import javax.inject.Inject

class FakeRepository @Inject constructor() : PhotoRepository {

    var photosServiceData: LinkedHashMap<String, Photo> = LinkedHashMap()

    override fun savePhoto(photo: Photo) {
        photosServiceData[photo.id] = photo
    }

    override suspend fun getPhotos(): Result<List<Photo>> {
        return Result.Success(photosServiceData.values.toList())
    }
}