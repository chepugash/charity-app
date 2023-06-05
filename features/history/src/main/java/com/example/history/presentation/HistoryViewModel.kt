package com.example.history.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.history.HistoryRouter
import com.example.history.domain.entity.TransactionEntity
import com.example.history.domain.usecase.GetHistoryUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val router: HistoryRouter
) : BaseViewModel() {

    private val _historyList = MutableLiveData<List<TransactionEntity>>()
    val historyList: LiveData<List<TransactionEntity>>
        get() = _historyList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getHistory() {
        viewModelScope.launch {
            try {
                _loading.value = true
                getHistoryUseCase().collect { list ->
                    _historyList.value = list
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun launchBack() {
        router.launchBack()
    }
}