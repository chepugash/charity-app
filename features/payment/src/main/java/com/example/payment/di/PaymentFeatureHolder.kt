package com.example.payment.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.payment.PaymentRouter
import javax.inject.Inject

class PaymentFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val paymentRouter: PaymentRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val paymentFeatureDependencies =
            DaggerPaymentFeatureComponent_PaymentFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerPaymentFeatureComponent.builder()
            .withDependencies(paymentFeatureDependencies)
            .router(paymentRouter)
            .build()
    }
}