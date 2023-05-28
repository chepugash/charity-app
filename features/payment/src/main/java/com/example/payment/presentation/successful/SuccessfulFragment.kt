package com.example.payment.presentation.successful

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.payment.data.PaymentApi
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
                launchBack()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<PaymentFeatureComponent>(this, PaymentApi::class.java)
            .successfulComponentFactory()
            .create(this)
            .inject(this)
    }

    private fun launchBack() {
        viewModel.launchCategories()
    }

    override fun subscribe(viewModel: SuccessfulViewModel) {
        with(viewModel) {

        }
    }

}