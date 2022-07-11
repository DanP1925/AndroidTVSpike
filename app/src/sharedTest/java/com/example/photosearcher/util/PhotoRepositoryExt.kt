package com.example.photosearcher.util

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.source.DefaultPhotoRepository
import kotlinx.coroutines.runBlocking

fun DefaultPhotoRepository.savePhoto(photo: Photo) = runBlocking{
    this@savePhoto.savePhoto(photo)
}