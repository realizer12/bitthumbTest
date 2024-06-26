package com.bithumb.realizer12.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


open class BaseActivity<VDB : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
    AppCompatActivity() {

    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.onCreate()
    }

    open fun VDB.onCreate() = Unit

    //토스트 보여주기
    protected fun showToast(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}