package com.example.payment.presentation.successful.di

import androidx.fragment.app.Fragment
import com.example.common.di.scope.ScreenScope
import com.example.payment.presentation.payment.PaymentFragment
import com.example.payment.presentation.payment.di.PaymentModule
import com.example.payment.presentation.successful.SuccessfulFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SuccessfulModule::class
    ]
)
@ScreenScope
interface SuccessfulComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): SuccessfulComponent
    }

    fun inject(fragment: SuccessfulFragment)
}