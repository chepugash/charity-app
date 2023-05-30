package com.example.payment.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.payment.PaymentRouter
import com.example.payment.presentation.payment.di.PaymentComponent
import com.example.payment.presentation.successful.di.SuccessfulComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        PaymentFeatureDependencies::class,
    ],
    modules = [
        PaymentFeatureModule::class
    ]
)
interface PaymentFeatureComponent {

    fun paymentComponentFactory(): PaymentComponent.Factory

    fun successfulComponentFactory(): SuccessfulComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(paymentRouter: PaymentRouter): Builder

        fun withDependencies(deps: PaymentFeatureDependencies): Builder

        fun build(): PaymentFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface PaymentFeatureDependenciesComponent: PaymentFeatureDependencies
}