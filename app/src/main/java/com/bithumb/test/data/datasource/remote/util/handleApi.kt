package com.bithumb.test.data.datasource.remote.util

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

fun<T:Any>handleApi(
    response: Response<T>,
): Result<T> {
    return try {
        val data = response.body()
        if (response.isSuccessful && data != null) {
            Result.success(data)
        } else {
            throw Exception(response.message())
        }
    }  catch (e: Throwable) {
        Result.failure(e)
    }
}