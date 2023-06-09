package com.example.sign.presentation.nouser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showToast
import com.example.sign.data.api.SignApi
import com.example.sign.di.SignFeatureComponent
import com.example.sign_up.databinding.FragmentNoUserBinding

class NoUserFragment : BaseFragment<NoUserViewModel>() {

    private lateinit var binding: FragmentNoUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)
        getUser()

        binding.run {
            tvSignIn.setOnClickListener {
                onSignInClick()
            }
            tvSignUp.setOnClickListener {
                onSignUpClick()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun inject() {
        FeatureUtils.getFeature<SignFeatureComponent>(this, SignApi::class.java)
            .noUserComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: NoUserViewModel) {
        with(viewModel) {
            user.observe(viewLifecycleOwner) {
                if (it != null) launchCategories()
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showError(it)
            }
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
    }

    private fun onSignUpClick() {
        viewModel.onSignUpClick()
    }

    private fun onSignInClick() {
        viewModel.onSignInClick()
    }

    private fun getUser() {
        viewModel.getUser()
    }

    private fun showLoading(flag: Boolean) {
        with(binding) {
            loading.isVisible = flag
            ivUser.isVisible = !flag
            tvHeader.isVisible = !flag
            tvSignUp.isVisible = !flag
            tvSignIn.isVisible = !flag
        }
    }

    private fun showError(error: Throwable) {
        binding.root.showToast(error.message ?: "Error")
    }
}