package com.example.photosearcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.source.FakeRepository
import com.example.photosearcher.data.source.MainCoroutineRule
import com.example.photosearcher.search.PhotoViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotosViewModelTest {

    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoRepository: FakeRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel(){
        photoRepository = FakeRepository()
        photoRepository.savePhoto(Photo("1", "Title 1", "Author 1", "2022-02-03"))
        photoRepository.savePhoto(Photo("2", "Title 2", "Author 2", "2022-03-04"))
        photoRepository.savePhoto(Photo("3", "Title 3", "Author 3", "2022-04-05"))
        photoViewModel = PhotoViewModel(photoRepository)
    }

    @Test
    fun loadAllPhotosFromRepository_loadingTogglesAndDataLoaded(){
        mainCoroutineRule.pauseDispatcher()
        photoViewModel.getPhotos()
        photoViewModel.photos.observeForTesting {
            assertThat(photoViewModel.dataLoading.getOrAwaitValue()).isTrue()
            mainCoroutineRule.resumeDispatcher()
            assertThat(photoViewModel.dataLoading.getOrAwaitValue()).isFalse()
            assertThat(photoViewModel.photos.getOrAwaitValue()).hasSize(3)
        }
    }

}