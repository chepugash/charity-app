package com.example.sign_up.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.sign_up.SignUpRouter
import com.example.sign_up.domain.usecase.SignUpUseCase
import com.example.sign_up.presentation.SignUpViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class SignUpModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): SignUpViewModel {
        return ViewModelProvider(fragment, factory)[SignUpViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun provideSignUpViewModel(
        useCase: SignUpUseCase,
        router: SignUpRouter): ViewModel {
        return SignUpViewModel(useCase, router)
    }
}