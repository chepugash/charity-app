package com.example.sign_up.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.sign_up.presentation.SignUpFragment
import com.example.sign_up.presentation.SignUpRouter
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@[Feature Component(dependencies = [SignUpDeps::class])]
internal interface SignUpComponent {

    fun inject(fragment: SignUpFragment)

    @Component.Builder
    interface Builder {

        fun deps(signUpDeps: SignUpDeps): Builder

        fun build(): SignUpComponent
    }
}

interface SignUpDeps {

    val router: SignUpRouter
}

interface SignUpDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: SignUpDeps

    companion object : SignUpDepsProvider by SignUpDepsStore
}

object SignUpDepsStore : SignUpDepsProvider {

    override var deps: SignUpDeps by notNull()
}

internal class SignUpComponentViewModel : ViewModel() {

    val component = DaggerSignUpComponent.builder().deps(SignUpDepsProvider.deps).build()
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature