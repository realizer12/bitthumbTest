package com.bithumb.realizer12.remote

import com.bithumb.realizer12.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.realizer12.data.model.PhotoDataModel
import com.bithumb.realizer12.data.repository.impl.PhotoRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Create Date: 2023/11/20
 *
 *
 * 레포지토리 동작 테스트용
 * @author LeeDongHun
 *
 *
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class PhotoRemoteRepositoryTest {
    private lateinit var photoRemoteDataSource: PhotoRemoteDataSource
    private lateinit var photoRepository: PhotoRepositoryImpl
    private val pageStart = 1
    private val pageLimit = 1
    private val data = PhotoDataModel()

    @Before
    fun setUp() {
        photoRemoteDataSource = mockk()
        photoRepository = PhotoRepositoryImpl(photoRemoteDataSource)
    }

    @Test
    fun testGetPhotoListOnSuccess(): Unit = runTest {

        coEvery {
            photoRemoteDataSource.getPhotoList(pageStart, pageLimit)
        } returns flowOf(Result.success(listOf(data)))

        photoRepository.getPhoto(pageStart).collectLatest {
            assertTrue(it.isSuccess)
            assertEquals(it, Result.success(listOf(data)))
        }
        coVerify {
            photoRemoteDataSource.getPhotoList(pageStart, pageLimit)
        }
    }


    @Test
    fun testGetPhotoListOnFailure(): Unit = runTest {
        coEvery {
            photoRemoteDataSource.getPhotoList(pageStart, pageLimit)
        } returns flowOf(Result.failure(Exception()))

        photoRepository.getPhoto(pageStart).collectLatest {
            assertTrue(it.isFailure)
            assertEquals(
                it.exceptionOrNull()?.message,
                Result.failure<List<PhotoDataModel>>(Exception()).exceptionOrNull()?.message
            )
        }
        coVerify {
            photoRemoteDataSource.getPhotoList(pageStart, pageLimit)
        }
    }

}