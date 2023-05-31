package com.example.charityapp.navigation

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.categories.CategoriesRouter
import com.example.categories.presentation.CategoriesViewModel
import com.example.charityapp.MainActivity
import com.example.charityapp.R
import com.example.favourite.FavouriteRouter
import com.example.foundation_info.FoundationRouter
import com.example.foundations.FoundationsRouter
import com.example.payment.PaymentRouter
import com.example.profile.ProfileRouter
import com.example.sign.SignRouter

class Navigator : SignRouter,
    ProfileRouter,
    CategoriesRouter,
    FoundationsRouter,
    FoundationRouter,
    PaymentRouter,
    FavouriteRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun launchSignIn() {
        navController?.navigate(R.id.signInFragment)
    }

    override fun launchSignUp() {
        navController?.navigate(R.id.signUpFragment)
    }

    override fun launchProfile() {
        navController?.navigate(R.id.profileFragment)
    }

    override fun launchNameDialog() {
        navController?.navigate(R.id.nameDialogFragment)
    }

    override fun launchSignOutDialog() {
        navController?.navigate(R.id.signOutDialogFragment)
    }

    override fun launchDeleteDialog() {
        navController?.navigate(R.id.deleteDialogFragment)
    }

    override fun launchPasswordDialog() {
        navController?.navigate(R.id.passwordDialogFragment)
    }

    override fun launchNoUser() {
        navController?.navigate(R.id.noUserFragment)
    }

    override fun launchFoundations(categoryId: Int) {
        val bundle = Bundle().apply {
            putInt(CATEGORY_ID, categoryId)
        }
        navController?.navigate(R.id.foundationsFragment, bundle)
    }

    override fun launchFoundationInfo(foundationId: Long) {
        val bundle = Bundle().apply {
            putLong(FOUNDATION_ID, foundationId)
        }
        navController?.navigate(R.id.foundationFragment, bundle)
    }

    override fun launchSuccessful() {
        navController?.navigate(R.id.successfulFragment)
    }

    override fun launchPayment(paymentInfo: String) {
        val bundle = Bundle().apply {
            putString(PAYMENT_INFO, paymentInfo)
        }
        navController?.navigate(R.id.paymentFragment, bundle)
    }

    override fun launchCategories() {
        navController?.navigate(R.id.categoriesFragment)
    }

    override fun launchBack() {
        navController?.popBackStack()
    }

    override fun openMain(context: Context) {
        MainActivity.start(context)
    }

    companion object {
        const val FOUNDATION_ID = "foundationId"
        const val CATEGORY_ID = "categoryId"
        const val PAYMENT_INFO = "paymentInfo"
    }
}