package com.bithumb.test.di

import com.bithumb.test.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.test.data.repository.PhotoRepository
import com.bithumb.test.data.repository.impl.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePhotoRepository(
        photoRemoteDataSource: PhotoRemoteDataSource
    ):PhotoRepository = PhotoRepositoryImpl(photoRemoteDataSource = photoRemoteDataSource)
}