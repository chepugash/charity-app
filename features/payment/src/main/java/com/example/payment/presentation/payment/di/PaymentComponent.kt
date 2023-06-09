package com.example.payment.presentation.payment.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.payment.presentation.payment.PaymentFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        PaymentModule::class
    ]
)
@ScreenScope
interface PaymentComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): PaymentComponent
    }

    fun inject(fragment: PaymentFragment)
}