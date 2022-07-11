package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

class PhotoDetailUrls(
    @SerializedName("url") val url: List<PhotoDetailUrl>
)