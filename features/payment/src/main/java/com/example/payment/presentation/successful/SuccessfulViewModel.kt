package com.example.payment.presentation.successful

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.base.BaseViewModel
import com.example.payment.PaymentRouter

class SuccessfulViewModel(
    private val router: PaymentRouter
) : BaseViewModel() {

    fun launchCategories() {
        router.launchCategories()
    }
}