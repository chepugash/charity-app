package com.example.foundation_info.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.foundation_info.FoundationRouter
import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.usecase.AddToFavouriteUseCase
import com.example.foundation_info.domain.usecase.GetFavouriteUseCase
import com.example.foundation_info.domain.usecase.GetFoundationUseCase
import com.example.foundation_info.domain.usecase.RemoveFromFavouriteUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch

class FoundationViewModel(
    private val getFoundationUseCase: GetFoundationUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val router: FoundationRouter
) : BaseViewModel() {

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    private val _foundation = MutableLiveData<FoundationEntity>()
    val foundation: LiveData<FoundationEntity>
        get() = _foundation

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getFoundation(query: Long) {
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

    fun isInFavourite(foundationEntity: FoundationEntity) {
        viewModelScope.launch {
            try {
                _loading.value = true
                getFavouriteUseCase().collect() {
                    _isFavourite.value = it.contains(foundationEntity.id)
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun addToFavourite(foundationEntity: FoundationEntity) {
        viewModelScope.launch {
            try {
                _loading.value = true
                addToFavouriteUseCase(foundationEntity)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            _isFavourite.value = true
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

    fun removeFromFavourite(foundationEntity: FoundationEntity) {
        viewModelScope.launch {
            try {
                _loading.value = true
                removeFromFavouriteUseCase(foundationEntity)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            _isFavourite.value = false
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

    fun goBack() {
        router.launchBack()
    }

    fun onDonateClick(foundationId: Long, foundationName: String) {
        router.launchPayment(foundationId, foundationName)
    }

}