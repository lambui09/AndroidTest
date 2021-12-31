package com.android.pixelteam.buiduclamtest.base

import DataResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val exception = MutableLiveData<Exception>()
    /**
     * Calls api with view model scope
     */
    private var loadingCount = 0
    protected fun <T> viewModelScope(
        liveData: MutableLiveData<T>,
        isShowLoading: Boolean = true,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        onRequest: suspend CoroutineScope.() -> DataResult<T>
    ) {
        viewModelScope.launch {
            showLoading(isShowLoading)
            when (val asynchronousTasks = onRequest(this)) {
                is DataResult.Success -> {
                    onSuccess?.invoke(asynchronousTasks.data) ?: kotlin.run {
                        liveData.value = asynchronousTasks.data
                    }
                }
                is DataResult.Error -> {
                    onError?.invoke(asynchronousTasks.exception) ?: kotlin.run {
                        exception.value = asynchronousTasks.exception
                    }
                }
            }
            hideLoading(isShowLoading)
        }
    }
    protected fun showLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount++
        if (isLoading.value != true) isLoading.value = true
    }

    protected fun hideLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount--
        if (loadingCount <= 0) {
            // reset loadingCount
            loadingCount = 0
            isLoading.value = false
        }
    }
}