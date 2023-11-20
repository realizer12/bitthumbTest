package com.bithumb.realizer12.data

import com.bithumb.realizer12.data.datasource.remote.impl.PhotoRemoteDataSourceImpl
import com.bithumb.realizer12.data.datasource.remote.retrofit.ApiService
import com.bithumb.realizer12.data.model.PhotoDataModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response


/**
 * Create Date: 2023/11/20
 *
 *
 * 데이터소스 동작 테스트용
 * @author LeeDongHun
 *
 *
**/
@OptIn(ExperimentalCoroutinesApi::class)
class PhotoRemoteDataSourceTest {

    private lateinit var  mockApiService:ApiService
    private lateinit var photoDataSource:PhotoRemoteDataSourceImpl
    private val pageStart = 1
    private val pageLimit = 1
    private val data = PhotoDataModel()


    @Before
    fun setUp(){
        mockApiService = mockk()
        photoDataSource = PhotoRemoteDataSourceImpl(mockApiService)
    }


    @Test
    fun testGetPhotoListOnSuccess():Unit = runTest {
        coEvery {
            mockApiService.getPhotoList(pageStart,pageLimit)
        } returns Response.success(listOf(data))
        photoDataSource.getPhotoList(pageStart,pageLimit).collectLatest {
            assertTrue(it.isSuccess)
            assertEquals(it,Result.success(listOf(data)))
        }
        coVerify {
            mockApiService.getPhotoList(pageStart, pageLimit)
        }
    }

    @Test
    fun testGetPhotoListOnFailure():Unit = runTest{

        val errorResponse:Response<List<PhotoDataModel>> = Response.error(400, "error"
            .toResponseBody("application/json".toMediaTypeOrNull()))

        coEvery {
            mockApiService.getPhotoList(pageStart,pageLimit)
        } returns errorResponse

        photoDataSource.getPhotoList(pageStart,pageLimit).collectLatest {
            assertTrue(it.isFailure)
            assertEquals(it.exceptionOrNull()?.message,photoDataSource.handleApi(errorResponse).exceptionOrNull()?.message)
        }
        coVerify {
            mockApiService.getPhotoList(pageStart, pageLimit)
        }
    }


}