package com.example.payment.presentation.payment.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.example.payment.PaymentRouter
import com.example.payment.domain.usecase.AddToHistoryUseCase
import com.example.payment.domain.usecase.CreateHistoryDocumentUseCase
import com.example.payment.presentation.payment.PaymentViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class PaymentModule {

    @Provides
    fun provideMainViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): PaymentViewModel {
        return ViewModelProvider(fragment, factory)[PaymentViewModel::class.java]
    }

    @Provides
    @IntoMap
    @ViewModelKey(PaymentViewModel::class)
    fun providePaymentViewModel(
        createHistoryDocumentUseCase: CreateHistoryDocumentUseCase,
        addToHistoryUseCase: AddToHistoryUseCase,
        router: PaymentRouter
    ): ViewModel = PaymentViewModel(
        createHistoryDocumentUseCase,
        addToHistoryUseCase,
        router
    )
}