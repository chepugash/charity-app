package com.example.payment.presentation.successful

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.payment.data.payment.PaymentApi
import com.example.payment.databinding.FragmentSuccessfulBinding
import com.example.payment.di.PaymentFeatureComponent

class SuccessfulFragment : BaseFragment<SuccessfulViewModel>() {

    private lateinit var binding: FragmentSuccessfulBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSuccessfulBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            btnSuccess.btnSubmit.setOnClickListener {
                goBack()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goBack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun inject() {
        FeatureUtils.getFeature<PaymentFeatureComponent>(this, PaymentApi::class.java)
            .successfulComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: SuccessfulViewModel) {
    }

    private fun goBack() {
        viewModel.launchCategories()
    }

}