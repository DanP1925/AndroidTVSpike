package com.example.photosearcher.data.source.remote

import android.util.Base64
import android.util.Base64.decode
import com.example.photosearcher.BuildConfig
import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result
import com.example.photosearcher.data.source.PhotoDataSource
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    val flickrService: FlickrService
) : PhotoDataSource {

    companion object {
        const val METHOD = "flickr.photos.search"
        const val TAG = "cat"
        const val FORMAT = "json"
        const val NOJSONCALLBACK = 1
    }

    private fun decodeAK(): String {
        val data: ByteArray = decode(BuildConfig.AK, Base64.DEFAULT)
        var param = ""
        try {
            param = String(data, Charset.defaultCharset())
        } catch (e: UnsupportedEncodingException) {
            throw e
        }
        return param
    }

    override suspend fun getPhotos(): Result<List<Photo>> {
        val response = flickrService.getPhotos(METHOD, decodeAK(), TAG, "", FORMAT, NOJSONCALLBACK)
        return if (response.isSuccessful) {
            val photos = response.body()?.photosResponse?.photos?.map {
                Photo(it.id)
            }
            if (photos != null) {
                Result.Success(photos)
            } else {
                Result.Error(Exception("Not found"))
            }
        } else {
            Result.Error(Exception(response.errorBody().toString()))
        }
    }

}