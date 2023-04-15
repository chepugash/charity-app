package com.example.charityapp.di.deps

import com.example.charityapp.App
import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.common.di.scope.ApplicationScope
import com.example.sign_up.di.SignUpFeatureApi
import com.example.sign_up.di.SignUpFeatureHolder
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ComponentHolderModule {

    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: App): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(SignUpFeatureApi::class)
    @IntoMap
    fun provideSignUpFeatureHolder(signUpFeatureHolder: SignUpFeatureHolder): FeatureApiHolder
}