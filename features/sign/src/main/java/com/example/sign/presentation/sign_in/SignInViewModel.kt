package com.example.sign.presentation.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.sign.SignRouter
import com.example.sign.domain.entity.AuthResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.usecase.SignInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val router: SignRouter
): BaseViewModel() {

    private val _authResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult>
        get() = _authResult

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
                val user = SignUserEntity(
                    email = email,
                    password = password,
                    repeatPassword = password
                )
                _authResult.value = signInUseCase.signIn(user)
                router.launchProfile()
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