package com.example.sign.presentation.nouser.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.sign.SignRouter
import com.example.sign.domain.usecase.GetUserUseCase
import com.example.sign.presentation.nouser.NoUserViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class NoUserModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): NoUserViewModel {
        return ViewModelProvider(fragment, factory)[NoUserViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(NoUserViewModel::class)
    fun provideNoUserViewModel(
        getUserUseCase: GetUserUseCase,
        router: SignRouter
    ): ViewModel {
        return NoUserViewModel(getUserUseCase, router)
    }
}