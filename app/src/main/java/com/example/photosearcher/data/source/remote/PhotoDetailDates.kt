package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

class PhotoDetailDates(
    @SerializedName("posted") val publishedTimeStamp: String
)