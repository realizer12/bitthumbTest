package com.bithumb.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bithumb.test.data.repository.PhotoRepository
import com.bithumb.test.presentation.model.PhotoPresentationModel
import com.bithumb.test.presentation.model.PhotoPresentationModel.Companion.fromData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
):ViewModel() {

    //error 처리
    private val _errorFlow = MutableSharedFlow<Throwable>(0,1, BufferOverflow.DROP_OLDEST)
    val errorFlow = _errorFlow.asSharedFlow()

    //photo 리스트 뷰로 보냄.
    private val _photoList = MutableStateFlow<List<PhotoPresentationModel>>(emptyList())
    val photoList = _photoList.asStateFlow()

    private var currentPage = 0

    private lateinit var getPhotoJob: Job
    private lateinit var clearPhotoJob: Job

    //photo 리스트 clear 하기
    fun clearPhotoList(getPhotoBlockDuration:Long) {
        clearPhotoJob = viewModelScope.launch{
            //현재 photo 가져오기  진행중이면 cancel 처리 해줌.
            if(::getPhotoJob.isInitialized && getPhotoJob.isActive){
                getPhotoJob.cancel()
            }
            //다시 page Reset
            currentPage = 0
            _photoList.emit(emptyList())

            //getphoto block 시키기
            delay(getPhotoBlockDuration)
        }
    }

    //제목으로 오름차순 하기
    fun sortWithTitle() = viewModelScope.launch(Dispatchers.IO){
        _photoList.emit(_photoList.value.sortedBy { it.title })
    }


    //새 photo 가져오기
   fun getNewPhoto(){
        getPhotoJob = viewModelScope.launch {
            if(::clearPhotoJob.isInitialized && clearPhotoJob.isActive){
                _errorFlow.emit(Throwable("잠시후 다시 시도 해주세요"))
                return@launch
            }
            currentPage++
            photoRepository.getPhoto(currentPage).collectLatest { result ->
                when {
                    result.isSuccess -> {
                        val finalResult = result.getOrDefault(emptyList()).map { it.fromData() }
                        _photoList.emit(_photoList.value.plus(finalResult).distinctBy { it.id })
                    }
                    result.isFailure ->{
                        result.exceptionOrNull()?.let { _errorFlow.emit(it) }
                    }
                }
            }
        }
   }

}