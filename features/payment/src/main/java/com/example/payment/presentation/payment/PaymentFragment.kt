package com.example.payment.presentation.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.payment.data.PaymentApi
import com.example.payment.databinding.FragmentPaymentBinding
import com.example.payment.di.PaymentFeatureComponent

class PaymentFragment : BaseFragment<PaymentViewModel>() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            btnSubmit.setOnClickListener {
                makeTransaction()
            }
            toolbar.tb.setNavigationOnClickListener {
                goBack()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<PaymentFeatureComponent>(this, PaymentApi::class.java)
            .paymentComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: PaymentViewModel) {
        with(viewModel) {
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showError(it)
            }
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
    }
    

    private fun goBack() {
        viewModel.goBack()
    }

    private fun makeTransaction() {
        viewModel.makeTransaction()
    }

    private fun showLoading(flag: Boolean) {
        with(binding) {
            if (flag) {
                loading.isVisible = true
                content.isVisible = false
            } else {
                loading.isVisible = false
                content.isVisible = true
            }
        }
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
    }
}