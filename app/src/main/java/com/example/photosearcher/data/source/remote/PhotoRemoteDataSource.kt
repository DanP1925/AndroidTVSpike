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
    private val flickrService: FlickrService
) : PhotoDataSource {

    companion object {
        const val GET_PHOTOS_METHOD = "flickr.photos.search"
        const val GET_PHOTO_METHOD = "flickr.photos.getInfo"
        const val TAG = "cat"
        const val FORMAT = "json"
        const val NO_JSON_CALLBACK = 1
        const val PER_PAGE = 20
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
        val response =
            flickrService.getPhotos(
                GET_PHOTOS_METHOD,
                decodeAK(),
                TAG,
                "",
                PER_PAGE,
                FORMAT,
                NO_JSON_CALLBACK
            )
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

    override suspend fun getPhotosWithQuery(query: String): Result<List<Photo>> {
        val response =
            flickrService.getPhotos(
                GET_PHOTOS_METHOD,
                decodeAK(),
                TAG,
                query,
                PER_PAGE,
                FORMAT,
                NO_JSON_CALLBACK
            )
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

    override suspend fun getPhoto(photoId: String): Result<Photo> {
        val response = flickrService.getPhotoDetail(
            GET_PHOTO_METHOD, decodeAK(), photoId, FORMAT, NO_JSON_CALLBACK
        )
        return if (response.isSuccessful) {
            val photo = response.body()?.photoDetailResponse
            if (photo == null) {
                Result.Error(Exception("Not found"))
            }
            return Result.Success(
                Photo(
                    photoId,
                    photo?.title?.title,
                    photo?.owner?.username,
                    photo?.dates?.publishedTimeStamp,
                    """https://live.staticflickr.com/${photo?.server}/${photo?.id}_${photo?.secret}_z.jpg"""
                )
            )
        } else {
            Result.Error(Exception(response.errorBody().toString()))
        }
    }

}