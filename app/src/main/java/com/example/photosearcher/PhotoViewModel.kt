package com.example.photosearcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.source.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos : LiveData<List<Photo>> = _photos

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getPhotos(){

    }

}