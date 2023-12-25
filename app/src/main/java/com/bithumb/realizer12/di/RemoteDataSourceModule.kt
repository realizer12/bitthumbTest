package com.bithumb.realizer12.di

import com.bithumb.realizer12.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.realizer12.data.datasource.remote.impl.PhotoRemoteDataSourceImpl
import com.bithumb.realizer12.data.datasource.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Create Date: 2023/11/20
 *
 *
 * remote datasource 의존성 주입  모듈
 * @author LeeDongHun
 *
 **/
@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providePhotoRemoteDataSource(
        apiService: ApiService
    ): PhotoRemoteDataSource = PhotoRemoteDataSourceImpl(apiService = apiService)

}