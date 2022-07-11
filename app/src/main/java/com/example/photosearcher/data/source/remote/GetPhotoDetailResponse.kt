package com.example.photosearcher.data.source.remote

import com.google.gson.annotations.SerializedName

class GetPhotoDetailResponse(
    @SerializedName("photo") val photoDetailResponse: PhotoDetailResponse
)