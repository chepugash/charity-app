package com.example.sign_up.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.sign_up.SignUpRouter
import com.example.sign_up.domain.entity.SignUpUserEntity
import com.example.sign_up.domain.usecase.SignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val router: SignUpRouter
): BaseViewModel() {

    private val _userInfo = MutableLiveData<SignUpUserEntity?>(null)
    val userInfo: LiveData<SignUpUserEntity?>
        get() = _userInfo

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun onSubmitClick(email: String, password: String, repeatedPassword: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                router.launchSignIn()
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun onSignInClick() {
        try {
            router.launchSignIn()
        } catch (error: Throwable) {
            _error.value = error
        }
    }
}