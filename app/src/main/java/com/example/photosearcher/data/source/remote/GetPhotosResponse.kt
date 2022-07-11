package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

data class GetPhotosResponse(
    @SerializedName("photos") val photosResponse: PhotosResponse
)