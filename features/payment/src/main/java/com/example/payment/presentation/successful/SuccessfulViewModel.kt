package com.example.payment.presentation.successful

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.payment.PaymentRouter

class SuccessfulViewModel(
    private val router: PaymentRouter
) : BaseViewModel() {

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun launchCategories() {
        router.launchCategories()
    }
}