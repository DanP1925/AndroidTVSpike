package com.example.photosearcher.data.source.remote

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result
import com.example.photosearcher.data.source.PhotoDataSource

class PhotoRemoteDataSource : PhotoDataSource {

    override suspend fun getPhotos(): Result<List<Photo>> {
        TODO("Not yet implemented")
    }

}