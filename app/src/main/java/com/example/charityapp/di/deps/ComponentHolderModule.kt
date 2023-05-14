package com.example.charityapp.di.deps

import com.example.charityapp.App
import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.common.di.scope.ApplicationScope
import com.example.profile.data.api.ProfileApi
import com.example.profile.di.ProfileFeatureHolder
import com.example.sign.data.api.SignApi
import com.example.sign.di.SignFeatureHolder
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
    @ClassKey(SignApi::class)
    @IntoMap
    fun provideSignFeatureHolder(signFeatureHolder: SignFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(ProfileApi::class)
    @IntoMap
    fun provideProfileFeatureHolder(profileFeatureHolder: ProfileFeatureHolder): FeatureApiHolder
}