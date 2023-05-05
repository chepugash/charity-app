package com.example.sign

import android.content.Context

interface SignUpRouter {

    fun launchSignIn()

    fun launchSignUp()

    fun openMain(context: Context)
}