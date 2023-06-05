package com.example.payment.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.FeatureScope
import com.example.payment.data.PaymentRepositoryImpl
import com.example.payment.data.firebase.FirebaseApi
import com.example.payment.data.firebase.FirebaseApiImpl
import com.example.payment.data.payment.PaymentApi
import com.example.payment.domain.repository.PaymentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class PaymentFeatureModule {

    @Provides
    @FeatureScope
    fun providePaymentRepository(
        paymentApi: PaymentApi,
        firebaseApi: FirebaseApi
    ): PaymentRepository = PaymentRepositoryImpl(paymentApi, firebaseApi)

    @Provides
    @FeatureScope
    fun providePaymentApi(
        networkApiCreator: NetworkApiCreator,
    ): PaymentApi = networkApiCreator.create(PaymentApi::class.java)

    @Provides
    @FeatureScope
    fun provideFirebaseApi(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): FirebaseApi = FirebaseApiImpl(auth, firestore)
}