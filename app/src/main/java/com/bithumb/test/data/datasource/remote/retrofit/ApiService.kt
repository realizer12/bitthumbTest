package com.bithumb.test.data.datasource.remote.retrofit

import com.bithumb.test.data.model.PhotoDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/photos")
    suspend fun getPhotoList(
        @Query("_start") pageStart:Int,
        @Query("_limit") pageLimit:Int
    ):Response<List<PhotoDataModel>>

}