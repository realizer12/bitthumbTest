package com.bithumb.realizer12.presentation

import com.bithumb.realizer12.data.model.PhotoDataModel
import com.bithumb.realizer12.data.repository.impl.PhotoRepositoryImpl
import com.bithumb.realizer12.presentation.model.PhotoPresentationModel.Companion.fromData
import com.bithumb.realizer12.presentation.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Create Date: 2023/11/20
 *
 * 메인 뷰모델 테스트용
 * @author LeeDongHun
 *
 *
**/
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var photoRepository: PhotoRepositoryImpl
    private lateinit var mainViewModel: MainViewModel
    private val pageStart = 0
    private val data = PhotoDataModel(
        albumId = 1,
        id = 1,
        title="title",
        url = "http://www.realizer12.com",
        thumbnailUrl = "http://www.realizer12.com"
    )
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        photoRepository = mockk()
        mainViewModel = MainViewModel(photoRepository)
        Dispatchers.setMain(dispatcher)
    }


    @Test
    fun testGetNewPhotoOnSuccess():Unit = runTest{
        coEvery {
            photoRepository.getPhoto(pageStart)
        } returns  flowOf(Result.success(listOf(data)))

        //getphoto 가져오기 실행
        mainViewModel.getNewPhoto()

        advanceUntilIdle()

        //최신 방출된 데이터의 id와 repository에서 받아온 data의 id가 같은지 검증
        assertEquals(mainViewModel.photoList.value.last().id,data.fromData().id)

        coVerify {
            photoRepository.getPhoto(pageStart)
        }
    }

    @Test
    fun testGetNewPhotoOnFailure():Unit = runTest{

        val errorMsg = "server-error"
        coEvery {
            photoRepository.getPhoto(pageStart)
        } returns  flowOf(Result.failure(Exception(errorMsg)))

        //getphoto 가져오기 실행
        mainViewModel.getNewPhoto()

        val errorFlowJob = launch {
            mainViewModel.errorFlow.collect {
                assertEquals(it.message,errorMsg)
            }
        }
        advanceUntilIdle()
        errorFlowJob.cancel()
        coVerify {
            photoRepository.getPhoto(pageStart)
        }
    }


}