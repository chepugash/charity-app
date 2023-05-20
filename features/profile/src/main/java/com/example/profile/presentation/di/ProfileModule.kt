package com.example.profile.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.scope.ScreenScope
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.profile.ProfileRouter
import com.example.profile.domain.usecase.ChangeNameUseCase
import com.example.profile.domain.usecase.ChangePasswordUseCase
import com.example.profile.domain.usecase.DeleteProfileUseCase
import com.example.profile.domain.usecase.GetUserUseCase
import com.example.profile.domain.usecase.SignOutUseCase
import com.example.profile.presentation.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class ProfileModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): ProfileViewModel {
        return ViewModelProvider(fragment, factory)[ProfileViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(
        changeNameUseCase: ChangeNameUseCase,
        changePasswordUseCase: ChangePasswordUseCase,
        deleteProfileUseCase: DeleteProfileUseCase,
        getUserUseCase: GetUserUseCase,
        signOutUseCase: SignOutUseCase,
        router: ProfileRouter
    ): ViewModel = ProfileViewModel(
        changeNameUseCase,
        changePasswordUseCase,
        deleteProfileUseCase,
        getUserUseCase,
        signOutUseCase,
        router
    )
}