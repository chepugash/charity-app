package com.example.favourite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.favourite.FavouriteRouter
import com.example.favourite.domain.entity.FoundationEntity
import com.example.favourite.domain.entity.UserEntity
import com.example.favourite.domain.usecase.CreateUserDocumentUseCase
import com.example.favourite.domain.usecase.GetFavouriteUseCase
import com.example.favourite.domain.usecase.GetUserUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val createUserDocumentUseCase: CreateUserDocumentUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val router: FavouriteRouter
) : BaseViewModel() {

    private val _user = MutableLiveData<UserEntity?>(null)
    val user: LiveData<UserEntity?>
        get() = _user

    private val _foundationList = MutableLiveData<List<FoundationEntity>>()
    val foundationList: LiveData<List<FoundationEntity>>
        get() = _foundationList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

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

    private fun createUserDocument() {
        viewModelScope.launch {
            try {
                createUserDocumentUseCase.invoke()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            getFavourite()
                        } else {
                            _error.value = it.exception
                        }
                    }
            } catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    fun getFavourite() {
        viewModelScope.launch {
            try {
                _loading.value = true
                getFavouriteUseCase().addOnCompleteListener {
                    if (it.isSuccessful) {
                        _foundationList.value = it.result
                        val e = it.exception
                        if (e is FirebaseFirestoreException
                            && e.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                            createUserDocument()
                        }
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

    fun launchFoundationInfo(foundationId: Long) {
        router.launchFoundationInfo(foundationId)
    }

    fun launchNoUser() {
        router.launchNoUser()
    }

}