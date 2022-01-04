package com.android.pixelteam.buiduclamtest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass
import androidx.lifecycle.ViewModelLazy
import com.android.pixelteam.buiduclamtest.utils.dialogManager.DialogManager
import com.android.pixelteam.buiduclamtest.utils.dialogManager.DialogManagerImpl

abstract class BaseActivity<viewModel : BaseVM>(viewModelClass: KClass<viewModel>) : AppCompatActivity(), BaseView{
    protected abstract fun getLayoutId() : Int
    protected val viewModel by ViewModelLazy(
        viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory })
    private var dialogManager : DialogManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        dialogManager = DialogManagerImpl(this)
    }

    override fun hideLoading() {
        dialogManager?.hideLoading()
    }

    override fun showLoading() {
        dialogManager?.showLoading()
    }

    override fun showLoading(isShow: Boolean) {
       if (isShow) showLoading() else hideLoading()
    }
}