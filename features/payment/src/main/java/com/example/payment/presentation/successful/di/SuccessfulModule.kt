package com.example.payment.presentation.successful.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.payment.PaymentRouter
import com.example.payment.presentation.payment.PaymentViewModel
import com.example.payment.presentation.successful.SuccessfulViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class SuccessfulModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): SuccessfulViewModel {
        return ViewModelProvider(fragment, factory)[SuccessfulViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(SuccessfulViewModel::class)
    fun provideSuccessfulViewModel(
        router: PaymentRouter
    ): ViewModel = SuccessfulViewModel(
        router
    )
}