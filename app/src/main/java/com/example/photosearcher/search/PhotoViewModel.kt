package com.example.photosearcher.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosearcher.Event
import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result
import com.example.photosearcher.data.source.PhotoRepository
import com.example.photosearcher.data.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _photos = MutableLiveData<Event<List<Photo>>>()
    val photos: LiveData<Event<List<Photo>>> = _photos

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getPhotos() {
        _dataLoading.value = true
        viewModelScope.launch {
            var resultPhotos = emptyList<Photo>()
            withContext(Dispatchers.IO) {
                val result = photoRepository.getPhotos()
                if (result.succeeded) {
                    resultPhotos = (result as Result.Success).data
                }
            }
            if (resultPhotos.isNotEmpty()){
                _photos.value = Event(resultPhotos)
            }
            _dataLoading.value = false
        }
    }

}