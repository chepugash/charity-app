package com.example.charityapp.di.deps

import com.example.categories.data.api.CategoriesApi
import com.example.categories.di.CategoriesFeatureHolder
import com.example.charityapp.App
import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.common.di.scope.ApplicationScope
import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.di.FavouriteFeatureHolder
import com.example.foundation_info.data.api.foundation.FoundationApi
import com.example.foundation_info.di.FoundationFeatureHolder
import com.example.foundations.data.api.FoundationsApi
import com.example.foundations.di.FoundationsFeatureHolder
import com.example.payment.data.payment.PaymentApi
import com.example.payment.di.PaymentFeatureHolder
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
    fun provideSignFeatureHolder(
        signFeatureHolder: SignFeatureHolder
    ): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(ProfileApi::class)
    @IntoMap
    fun provideProfileFeatureHolder(
        profileFeatureHolder: ProfileFeatureHolder
    ): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(CategoriesApi::class)
    @IntoMap
    fun provideCategoriesFeatureHolder(
        categoriesFeatureHolder: CategoriesFeatureHolder
    ): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(FoundationsApi::class)
    @IntoMap
    fun provideFoundationsFeatureHolder(
        foundationsFeatureHolder: FoundationsFeatureHolder
    ): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(FoundationApi::class)
    @IntoMap
    fun provideFoundationFeatureHolder(
        foundationFeatureHolder: FoundationFeatureHolder
    ): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(PaymentApi::class)
    @IntoMap
    fun providePaymentFeatureHolder(
        paymentFeatureHolder: PaymentFeatureHolder
    ): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(FirebaseApi::class)
    @IntoMap
    fun provideFavouriteFeatureHolder(
        favouriteFeatureHolder: FavouriteFeatureHolder
    ): FeatureApiHolder
}