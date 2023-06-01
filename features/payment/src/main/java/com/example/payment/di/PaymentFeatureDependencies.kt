package com.example.payment.di

import com.example.common.data.network.NetworkApiCreator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface PaymentFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator

    fun firebaseAuth(): FirebaseAuth

    fun firestore(): FirebaseFirestore
}