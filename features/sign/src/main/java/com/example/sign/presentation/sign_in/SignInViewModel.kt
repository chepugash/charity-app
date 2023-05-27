package com.example.sign.presentation.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.sign.SignRouter
import com.example.sign.domain.entity.ApiResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.usecase.SignInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val router: SignRouter
): BaseViewModel() {

    private val _apiResult = MutableLiveData<ApiResult>()
    val apiResult: LiveData<ApiResult>
        get() = _apiResult

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun onSubmitClick(user: SignUserEntity) {
        viewModelScope.launch {
            try {
                _loading.value = true
                signInUseCase.signIn(user).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _apiResult.value = ApiResult.Success
                    } else {
                        _apiResult.value = ApiResult.Error(it.exception?.message ?: "Error")
                    }
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun onSignUpClick() {
        try {
            router.launchSignUp()
        } catch (error: Throwable) {
            _error.value = error
        }
    }

    fun launchProfile() {
        try {
            router.launchProfile()
        } catch (error: Throwable) {
            _error.value = error
        }
    }
}