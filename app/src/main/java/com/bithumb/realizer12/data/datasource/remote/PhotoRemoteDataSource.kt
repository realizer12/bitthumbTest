package com.bithumb.realizer12.data.datasource.remote

import com.bithumb.realizer12.data.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

interface PhotoRemoteDataSource {
    suspend fun getPhotoList(pageStart:Int,pageLimit:Int): Flow<Result<List<PhotoDataModel>>>
}