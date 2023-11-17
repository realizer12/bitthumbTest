package com.bithumb.test.data.datasource.remote

import com.bithumb.test.data.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

interface PhotoRemoteDataSource {
    suspend fun getPhotoList(pageStart:Int,pageLimit:Int): Flow<Result<List<PhotoDataModel>>>
}