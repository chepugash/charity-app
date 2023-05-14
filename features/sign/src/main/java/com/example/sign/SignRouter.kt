package com.example.sign

import android.content.Context

interface SignRouter {

    fun launchSignIn()

    fun launchSignUp()

    fun launchProfile()

    fun openMain(context: Context)
}