package com.example.sign.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.sign.SignRouter
import com.example.sign.domain.entity.ApiResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.usecase.SignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
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

    fun onSubmitClick(email: String, password: String, repeatPassword: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                signUpUseCase.invoke(
                    SignUserEntity(email, password, repeatPassword)
                ).addOnCompleteListener {
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

    fun onSignInClick() {
        launchSignIn()
    }

    fun launchSignIn() {
        router.launchSignIn()
    }
}