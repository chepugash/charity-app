package com.example.sign_up.presentation

import androidx.appcompat.app.ActionBar.NavigationMode
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sign_up.domain.entity.SignUpUserEntity
import com.example.sign_up.domain.usecase.SignUpUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
): ViewModel() {

    private val _userEntity = MutableLiveData<SignUpUserEntity?>(null)
    val userEntity: LiveData<SignUpUserEntity?>
        get() =_userEntity

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    fun signUp(userEntity: SignUpUserEntity) {
        viewModelScope.launch {
            try {
                signUpUseCase.signUp(userEntity)
            } catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    companion object {
        fun provideFactory(
            signUpUseCase: SignUpUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignUpViewModel(signUpUseCase)
            }
        }
    }
}