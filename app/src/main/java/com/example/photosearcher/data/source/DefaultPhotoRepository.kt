package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultPhotoRepository @Inject constructor(
    private val remoteDataSource: PhotoDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PhotoRepository {

    override fun savePhoto(photo: Photo) {
    }

    override suspend fun getPhotos(): Result<List<Photo>> {
        return remoteDataSource.getPhotos()
    }


}