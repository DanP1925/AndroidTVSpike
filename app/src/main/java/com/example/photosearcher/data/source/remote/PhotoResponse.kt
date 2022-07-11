package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id") val id: String
)
