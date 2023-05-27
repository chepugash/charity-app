package com.example.payment.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.FeatureScope
import com.example.payment.data.PaymentApi
import com.example.payment.data.PaymentRepositoryImpl
import com.example.payment.domain.repository.PaymentRepository
import dagger.Module
import dagger.Provides

@Module
class PaymentFeatureModule {

    @Provides
    @FeatureScope
    fun providePaymentRepository(
        paymentApi: PaymentApi
    ): PaymentRepository = PaymentRepositoryImpl(paymentApi)

    @Provides
    @FeatureScope
    fun providePaymentApi(
        networkApiCreator: NetworkApiCreator,
    ): PaymentApi = networkApiCreator.create(PaymentApi::class.java)
}