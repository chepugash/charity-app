package com.example.sign.presentation.signup.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.sign.SignRouter
import com.example.sign.domain.usecase.CreateUserDocumentUseCase
import com.example.sign.domain.usecase.SignUpUseCase
import com.example.sign.presentation.signup.SignUpViewModel
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
        signUpUseCase: SignUpUseCase,
        createUserDocumentUseCase: CreateUserDocumentUseCase,
        router: SignRouter
    ): ViewModel = SignUpViewModel(
        signUpUseCase,
        createUserDocumentUseCase,
        router
    )
}