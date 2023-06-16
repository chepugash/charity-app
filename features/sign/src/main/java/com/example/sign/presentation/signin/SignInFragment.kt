package com.example.sign.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showToast
import com.example.sign.data.api.SignApi
import com.example.sign.di.SignFeatureComponent
import com.example.sign.domain.entity.ApiResult
import com.example.sign_up.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<SignInViewModel>() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            btnSubmit.setOnClickListener {
                onSubmitClick(
                    email.itEmail.text.toString(),
                    password.itPassword.text.toString()
                )
            }
            tvSignIn.setOnClickListener {
                onSignUpClick()
            }
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SignFeatureComponent>(this, SignApi::class.java)
            .signInComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: SignInViewModel) {
        with(viewModel) {
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            apiResult.observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResult.Success -> {
                        viewModel.launchCategories()
                    }

                    is ApiResult.Error -> {
                        showError(it.message)
                    }
                }
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showError(it)
            }
        }
    }

    private fun showLoading(flag: Boolean) {
        with(binding) {
            ivEntrance.isVisible = !flag
            tvCreate.isVisible = !flag
            email.tfEmail.isVisible = !flag
            password.tfPassword.isVisible = !flag
            tvSignIn.isVisible = !flag
            btnSubmit.isVisible = !flag
            loading.isVisible = flag
        }
    }

    private fun onSubmitClick(email: String, password: String) {
        viewModel.onSubmitClick(email, password)
    }

    private fun onSignUpClick() {
        viewModel.onSignUpClick()
    }

    private fun showError(error: Throwable) {
        binding.root.showToast(error.message ?: "Error")
    }

    private fun showError(message: String) {
        binding.root.showToast(message)
    }
}