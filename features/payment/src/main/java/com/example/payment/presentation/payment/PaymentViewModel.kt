package com.example.payment.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.payment.PaymentRouter
import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.usecase.AddToHistoryUseCase
import kotlinx.coroutines.launch
import java.util.Date

class PaymentViewModel(
    private val addToHistoryUseCase: AddToHistoryUseCase,
    private val router: PaymentRouter
) : BaseViewModel() {

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun addToHistory(foundationId: Long, foundationName: String, sum: Long) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val transactionEntity = TransactionEntity(Date(), foundationId, foundationName, sum)
                addToHistoryUseCase(transactionEntity).addOnCompleteListener {
                    if (it.isSuccessful) {
                        launchSuccessful()
                    } else {
                        _error.value = it.exception
                    }
                }
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