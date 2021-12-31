package com.android.pixelteam.buiduclamtest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass
import androidx.lifecycle.ViewModelLazy

abstract class BaseActivity<viewModel : BaseVM>(viewModelClass: KClass<viewModel>) : AppCompatActivity(), BaseView{
    protected abstract fun getLayoutId() : Int
    protected val viewModel by ViewModelLazy(
        viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showLoading(isShow: Boolean) {
        TODO("Not yet implemented")
    }
}