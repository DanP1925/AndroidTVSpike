package com.example.photosearcher.di

import com.example.photosearcher.data.source.DefaultPhotoRepository
import com.example.photosearcher.data.source.PhotoDataSource
import com.example.photosearcher.data.source.PhotoRepository
import com.example.photosearcher.data.source.remote.PhotoRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePhotoRemoteDataSource(): PhotoDataSource{
        return PhotoRemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}

@Module
@InstallIn(SingletonComponent::class)
object PhotoRepositoryModule {

    @Singleton
    @Provides
    fun providePhotoRepository(
        remoteDataSource: PhotoRemoteDataSource,
        ioDispatcher: CoroutineDispatcher
    ): PhotoRepository = DefaultPhotoRepository(
        remoteDataSource,
        ioDispatcher
    )

}