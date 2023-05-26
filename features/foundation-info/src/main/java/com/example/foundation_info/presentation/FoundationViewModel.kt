package com.example.foundation_info.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.foundation_info.FoundationRouter
import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.usecase.GetFoundationUseCase
import kotlinx.coroutines.launch

class FoundationViewModel(
    private val getFoundationUseCase: GetFoundationUseCase,
    private val router: FoundationRouter
) : BaseViewModel() {

    private val _foundation = MutableLiveData<FoundationEntity>()
    val foundation: LiveData<FoundationEntity>
        get() = _foundation

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getFoundation(query: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _foundation.value = getFoundationUseCase.invoke(query)
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun goBack() {
        router.launchBack()
    }
}