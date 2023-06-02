package com.example.sign.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.sign.SignRouter
import com.example.sign.domain.entity.ApiResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.usecase.SignInUseCase
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

    fun onSubmitClick(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                signInUseCase.invoke(
                    SignUserEntity(email, password)
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

    fun onSignUpClick() {
        router.launchSignUp()
    }

    fun launchCategories() {
        router.launchCategories()
    }
}