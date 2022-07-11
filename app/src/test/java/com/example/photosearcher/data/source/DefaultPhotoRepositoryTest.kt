package com.example.photosearcher.data.source

import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DefaultPhotoRepositoryTest {

    private val photo1 = Photo("Title 1", "Author 1", "2022-02-03")
    private val photo2 = Photo("Title 2", "Author 2", "2022-03-04")
    private val photo3 = Photo("Title 3", "Author 3", "2022-04-05")
    private val remotePhotos = listOf(photo1, photo2, photo3)

    private lateinit var photoRemoteDataSource: FakeDataSource

    private lateinit var repository: DefaultPhotoRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createRepository() {
        photoRemoteDataSource = FakeDataSource(remotePhotos.toMutableList())
        repository = DefaultPhotoRepository(photoRemoteDataSource, Dispatchers.Main)
    }

    @Test
    fun getPhotos_requestAllPhotosFromRemoteDataSource() = mainCoroutineRule.runBlockingTest {
        val photos = repository.getPhotos() as Result.Success
        assertThat(photos.data).isEqualTo(remotePhotos)
    }

}