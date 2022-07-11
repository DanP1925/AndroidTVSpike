package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

class PhotoDetailTitle(
    @SerializedName("_content") val title: String
)