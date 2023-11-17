package com.bithumb.test.data.datasource.remote.impl

import android.util.Log
import com.bithumb.test.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.test.data.datasource.remote.retrofit.ApiService
import com.bithumb.test.data.datasource.remote.util.handleApi
import com.bithumb.test.data.model.PhotoDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
):PhotoRemoteDataSource{
    override suspend fun getPhotoList(pageStart:Int,pageLimit:Int): Flow<Result<List<PhotoDataModel>>> = flow {
        runCatching {
            apiService.getPhotoList(pageStart = pageStart, pageLimit = pageLimit)
        }.onSuccess {
            emit(handleApi(it))
        }.onFailure {
            emit(Result.failure(it))
        }
    }

}