package com.bithumb.test.presentation.activity

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bithumb.test.R
import com.bithumb.test.databinding.ActivityMainBinding
import com.bithumb.test.presentation.adapter.PhotoRvAdapter
import com.bithumb.test.presentation.base.BaseActivity
import com.bithumb.test.presentation.util.setOnClick
import com.bithumb.test.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var photoListRvAdapter: PhotoRvAdapter

    //main 뷰모델
    private val mainViewModel:MainViewModel by viewModels()

    override fun ActivityMainBinding.onCreate() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    //초기세팅
    private fun initSet(){
        photoListRvAdapter = PhotoRvAdapter()
        binding.rvPhotoList.apply {
            adapter = photoListRvAdapter
        }
    }


    //리스너 이벤트 모음
    private fun setListenerEvent(){

        //읽기 버튼 클릭
        binding.btnRead.setOnClick(uiScope = lifecycleScope, skipDuration = 500){
            mainViewModel.getNewPhoto()
        }

        //clear 버튼 클릭
        binding.btnClear.setOnClickListener {
            mainViewModel.clearPhotoList(getPhotoBlockDuration = 3000L)
        }

        //sort 버튼 클릭
        binding.btnSort.setOnClickListener {
           mainViewModel.sortWithTitle()
        }
    }


    //뷰모델로부터 데이터 받아옴.
    private fun getDataFromVm() = lifecycleScope.launch{
        repeatOnLifecycle(Lifecycle.State.STARTED){
            launch {
                mainViewModel.errorFlow.collectLatest {
                    showToast(it.message.toString())
                }
            }
            launch {
                mainViewModel.photoList.collectLatest {
                    photoListRvAdapter.submitList(it){

                        //마지막 리스트로 scroll 해줌.
                        binding.rvPhotoList.scrollToPosition(it.lastIndex)
                    }
                }
            }
        }
    }

}