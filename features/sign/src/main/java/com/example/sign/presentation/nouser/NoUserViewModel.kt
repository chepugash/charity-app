package com.example.sign.presentation.nouser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.sign.SignRouter
import com.example.sign.domain.entity.SessionUserEntity
import com.example.sign.domain.usecase.GetUserUseCase
import kotlinx.coroutines.launch

class NoUserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val router: SignRouter
) : BaseViewModel() {

    private val _user = MutableLiveData<SessionUserEntity?>(null)
    val user: LiveData<SessionUserEntity?>
        get() = _user

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

    fun onSignInClick() {
        router.launchSignIn()
    }

    fun onSignUpClick() {
        router.launchSignUp()
    }

    fun launchCategories() {
        router.launchCategories()
    }
}