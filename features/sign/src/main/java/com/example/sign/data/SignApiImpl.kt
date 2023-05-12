package com.example.sign.data

import com.example.common.data.network.NetworkApiCreator
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignApiImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : SignApi {

}