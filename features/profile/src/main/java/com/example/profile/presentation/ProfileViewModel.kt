package com.example.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.profile.ProfileRouter
import com.example.profile.domain.entity.ProfileUserEntity
import com.example.profile.domain.usecase.ChangeNameUseCase
import com.example.profile.domain.usecase.ChangePasswordUseCase
import com.example.profile.domain.usecase.DeleteProfileUseCase
import com.example.profile.domain.usecase.GetUserUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val changeNameUseCase: ChangeNameUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val router: ProfileRouter
): BaseViewModel() {

    private val _user = MutableLiveData<ProfileUserEntity?>()
    val user: LiveData<ProfileUserEntity?>
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
                _user.value = getUserUseCase.getUser()
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }
}