package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photo") val photos: List<PhotoResponse>
)