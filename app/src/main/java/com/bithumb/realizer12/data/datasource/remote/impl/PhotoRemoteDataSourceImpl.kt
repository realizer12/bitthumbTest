package com.bithumb.realizer12.data.datasource.remote.impl

import androidx.annotation.VisibleForTesting
import com.bithumb.realizer12.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.realizer12.data.datasource.remote.retrofit.ApiService
import com.bithumb.realizer12.data.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Create Date: 2023/11/20
 *
 *
 * photo 관련 remote 기능들이 구현된 class
 *
 * @see PhotoRemoteDataSource
 * @author LeeDongHun
 *
 **/
class PhotoRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
):PhotoRemoteDataSource {
    override suspend fun getPhotoList(
        pageStart: Int,
        pageLimit: Int
    ): Flow<Result<List<PhotoDataModel>>> = flow {
        runCatching {
            apiService.getPhotoList(pageStart = pageStart, pageLimit = pageLimit)
        }.onSuccess {
            emit(handleApi(it))
        }.onFailure {
            emit(Result.failure(it))
        }
    }




    //가이드상은 안좋은 방법
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun <T : Any> handleApi(
        response: Response<T>,
    ): Result<T> {
        return try {
            val data = response.body()
            if (response.isSuccessful && data != null) {
                Result.success(data)
            } else {
                throw Exception(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
