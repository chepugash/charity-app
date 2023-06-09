package com.example.foundations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.foundations.FoundationsRouter
import com.example.foundations.domain.entity.FoundationEntity
import com.example.foundations.domain.usecase.GetFoundationsUseCase
import kotlinx.coroutines.launch

class FoundationsViewModel(
    private val getFoundationsUseCase: GetFoundationsUseCase,
    private val router: FoundationsRouter
) : BaseViewModel() {

    private val _category = MutableLiveData<String>()
    val category: LiveData<String>
        get() = _category

    private val _foundationList = MutableLiveData<List<FoundationEntity>>()
    val foundationList: LiveData<List<FoundationEntity>>
        get() = _foundationList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getFoundations(query: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                getFoundationsUseCase(query).run {
                    _foundationList.value = this.foundations
                    _category.value = this.name
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun launchFoundation(id: Long) {
        router.launchFoundationInfo(id)
    }

    fun goBack() {
        router.launchBack()
    }
}