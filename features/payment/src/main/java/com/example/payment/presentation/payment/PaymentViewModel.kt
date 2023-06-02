package com.example.payment.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.payment.PaymentRouter
import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.usecase.AddToHistoryUseCase
import com.example.payment.domain.usecase.CreateHistoryDocumentUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import java.util.Date

class PaymentViewModel(
    private val createHistoryDocumentUseCase: CreateHistoryDocumentUseCase,
    private val addToHistoryUseCase: AddToHistoryUseCase,
    private val router: PaymentRouter
) : BaseViewModel() {

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private fun createHistoryDocument(foundationId: Long, foundationName: String, sum: Long) {
        viewModelScope.launch {
            try {
                createHistoryDocumentUseCase().addOnCompleteListener {
                    if (it.isSuccessful) {
                        addToHistory(foundationId, foundationName, sum)
                    } else {
                        _error.value = it.exception
                    }
                }
            } catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    fun addToHistory(foundationId: Long, foundationName: String, sum: Long) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val transactionEntity = TransactionEntity(Date(), foundationId, foundationName, sum)
                addToHistoryUseCase(transactionEntity).addOnCompleteListener {
                    if (it.isSuccessful) {
                        launchSuccessful()
                    } else {
                        val e = it.exception
                        if (e is FirebaseFirestoreException
                            && e.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                            createHistoryDocument(foundationId, foundationName, sum)
                        } else {
                            _error.value = it.exception
                        }
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