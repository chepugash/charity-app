package com.example.profile.presentation

import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.profile.ProfileRouter
import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.example.profile.domain.usecase.ChangeNameUseCase
import com.example.profile.domain.usecase.ChangePasswordUseCase
import com.example.profile.domain.usecase.DeleteProfileUseCase
import com.example.profile.domain.usecase.GetUserUseCase
import com.example.profile.domain.usecase.SignOutUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val changeNameUseCase: ChangeNameUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val router: ProfileRouter
): BaseViewModel() {

    private val _apiResult = MutableLiveData<ApiResult>(ApiResult.Success)
    val apiResult: LiveData<ApiResult>
        get() = _apiResult

    private val _user = MutableLiveData<ProfileUserEntity?>(null)
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

    fun changeName(name: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                changeNameUseCase.changeName(name)
                    ?.addOnCompleteListener {
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

    fun changePassword(password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                changePasswordUseCase.changePassword(password)
                    ?.addOnCompleteListener {
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

    fun deleteProfile() {
        viewModelScope.launch {
            try {
                _loading.value = true
                deleteProfileUseCase.deleteProfile()
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            launchSignIn()
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

    fun signOut() {
        viewModelScope.launch {
            try {
                _loading.value = true
                signOutUseCase.signOut()
                launchSignIn()
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    fun onChangeName() {
        router.launchNameDialog()
    }

    fun onSignOut() {
        router.launchSignOutDialog()
    }

    fun onDelete() {
        router.launchDeleteDialog()
    }

    fun onChangePassword() {
        router.launchPasswordDialog()
    }

    fun launchSignIn() {
        router.launchSignIn()
    }
}