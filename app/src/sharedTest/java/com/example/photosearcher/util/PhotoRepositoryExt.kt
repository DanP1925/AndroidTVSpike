package com.example.photosearcher.util

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.source.PhotoRepository
import kotlinx.coroutines.runBlocking

fun PhotoRepository.savePhoto(photo: Photo) = runBlocking{
    this@savePhoto.savePhoto(photo)
}