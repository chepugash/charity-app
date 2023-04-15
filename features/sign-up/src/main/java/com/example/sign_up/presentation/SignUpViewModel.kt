package com.example.sign_up.presentation

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.common.base.BaseViewModel
import com.example.sign_up.SignUpRouter
import com.example.sign_up.domain.entity.SignUpUserEntity
import com.example.sign_up.domain.usecase.SignUpUseCase
import com.example.sign_up.presentation.di.SignUpModule
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val router: SignUpRouter
): BaseViewModel() {

    private val _userLiveData = MutableLiveData<SignUpUserEntity>()
    val userLiveData: LiveData<SignUpUserEntity> = _userLiveData

    private fun userUpdateSuccess() {
    }

    private fun userUpdateError(error: Throwable) {
    }
}