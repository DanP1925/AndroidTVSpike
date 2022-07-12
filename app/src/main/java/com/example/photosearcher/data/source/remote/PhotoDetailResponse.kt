package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

class PhotoDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: String,
    @SerializedName("owner") val owner: PhotoDetailOwner,
    @SerializedName("title") val title: PhotoDetailTitle,
    @SerializedName("dates") val dates: PhotoDetailDates
)
