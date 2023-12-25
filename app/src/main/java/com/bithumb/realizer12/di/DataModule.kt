package com.bithumb.realizer12.di

import com.bithumb.realizer12.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.realizer12.data.repository.PhotoRepository
import com.bithumb.realizer12.data.repository.impl.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create Date: 2023/11/20
 *
 *
 * repository 의존성 주입 모듈
 * @author LeeDongHun
 *
 **/
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePhotoRepository(
        photoRemoteDataSource: PhotoRemoteDataSource
    ):PhotoRepository = PhotoRepositoryImpl(photoRemoteDataSource = photoRemoteDataSource)
}