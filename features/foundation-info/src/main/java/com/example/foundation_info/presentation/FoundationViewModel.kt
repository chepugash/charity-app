package com.example.foundation_info.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.foundation_info.FoundationRouter
import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.entity.FoundationUserEntity
import com.example.foundation_info.domain.usecase.AddToFavouriteUseCase
import com.example.foundation_info.domain.usecase.CreateUserDocumentUseCase
import com.example.foundation_info.domain.usecase.GetFavouriteUseCase
import com.example.foundation_info.domain.usecase.GetFoundationUseCase
import com.example.foundation_info.domain.usecase.GetUserUseCase
import com.example.foundation_info.domain.usecase.RemoveFromFavouriteUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch

class FoundationViewModel(
    private val getFoundationUseCase: GetFoundationUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val createUserDocumentUseCase: CreateUserDocumentUseCase,
    private val router: FoundationRouter
) : BaseViewModel() {

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    private val _user = MutableLiveData<FoundationUserEntity?>(null)
    val user: LiveData<FoundationUserEntity?>
        get() = _user

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

    fun getUser() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _user.value = getUserUseCase()
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    private fun createUserDocument(foundationEntity: FoundationEntity) {
        viewModelScope.launch {
            try {
                createUserDocumentUseCase.invoke()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            addToFavourite(foundationEntity)
                        } else {
                            _error.value = it.exception
                        }
                    }
            } catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    fun isInFavourite(foundationEntity: FoundationEntity) {
        viewModelScope.launch {
            try {
                _loading.value = true
                getFavouriteUseCase().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val favourite = it.result as ArrayList<Long>
                        _isFavourite.value = favourite.contains(foundationEntity.id.toLong())
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

    fun addToFavourite(foundationEntity: FoundationEntity) {
        viewModelScope.launch {
            try {
                _loading.value = true
                addToFavouriteUseCase(foundationEntity)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            _isFavourite.value = true
                        } else {
                            val e = it.exception
                            if (e is FirebaseFirestoreException
                                && e.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                                createUserDocument(foundationEntity)
                            } else {
                                _error.value = it.exception
                            }
                        }
                    }
            } catch(error: Throwable) {
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
            } catch(error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun goBack() {
        router.launchBack()
    }

    fun onDonateClick(paymentInfo: String) {
        router.launchPayment(paymentInfo)
    }

}