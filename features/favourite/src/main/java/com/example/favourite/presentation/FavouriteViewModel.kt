package com.example.favourite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.favourite.FavouriteRouter
import com.example.favourite.domain.entity.FoundationEntity
import com.example.favourite.domain.usecase.GetFavouriteUseCase
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val router: FavouriteRouter
) : BaseViewModel() {

    private val _foundationList = MutableLiveData<List<FoundationEntity>>()
    val foundationList: LiveData<List<FoundationEntity>>
        get() = _foundationList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getFavourite() {
        viewModelScope.launch {
            try {
                _loading.value = true
                getFavouriteUseCase().collect { list ->
                    _foundationList.value = list
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun launchFoundationInfo(foundationId: Long) {
        router.launchFoundationInfo(foundationId)
    }

}