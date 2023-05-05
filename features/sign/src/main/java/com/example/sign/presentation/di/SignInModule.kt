package com.example.sign.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.sign.SignUpRouter
import com.example.sign.domain.usecase.SignUpUseCase
import com.example.sign.presentation.SignInViewModel
import com.example.sign.presentation.SignUpViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class SignInModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): SignInViewModel {
        return ViewModelProvider(fragment, factory)[SignInViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun provideSignInViewModel(
        router: SignUpRouter
    ): ViewModel {
        return SignInViewModel(router)
    }
}