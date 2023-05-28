package com.example.payment.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.payment.PaymentRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val router: PaymentRouter
) : BaseViewModel() {

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun makeTransaction() {
        viewModelScope.launch {
            try {
                _loading.value = true
                // FOR SCREENCAST ONLY
                launchSuccessful()
                delay(1000)
                // end FOR SCREENCAST ONLY
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    private fun launchSuccessful() {
        router.launchSuccessful()
    }

    fun goBack() {
        router.launchBack()
    }
}